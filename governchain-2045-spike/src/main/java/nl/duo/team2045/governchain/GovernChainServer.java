package nl.duo.team2045.governchain;

import org.ethereum.config.SystemProperties;
import org.ethereum.config.blockchain.FrontierConfig;
import org.ethereum.facade.Ethereum;
import org.ethereum.util.blockchain.SolidityContract;
import org.ethereum.util.blockchain.StandaloneBlockchain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * GovernChainServer
 *
 * @author geertjan
 */
@Component
public class GovernChainServer {
    private static final Logger LOG = LoggerFactory.getLogger(GovernChainServer.class);

    @Inject
    private Resource contract; //start with one contract
//
//    @Inject
//    private Ethereum ethereum;

    public GovernChainServer(){

    }

//    @Inject
//    public GovernChainServer(@NotNull Ethereum ethereum) {
//        this.ethereum = ethereum;
//    }

    @PostConstruct
    public void start(){

        SystemProperties.getDefault().setBlockchainConfig(new FrontierConfig(new FrontierConfig.FrontierConstants() {
            @Override
            public BigInteger getMINIMUM_DIFFICULTY() {
                return BigInteger.ONE;
            }
        }));

        // Creating a blockchain which generates a new block for each transaction
        // just not to call createBlock() after each call transaction
        StandaloneBlockchain bc = new StandaloneBlockchain().withAutoblock(true);
        LOG.info("Creating first empty block (need some time to generate DAG)...");
        // warning up the block miner just to understand how long
        // the initial miner dataset is generated
        bc.createBlock();
        LOG.info("Creating a contract...");
        // This compiles our Solidity contract, submits it to the blockchain
        // internally generates the block with this transaction and returns the
        // contract interface
        StringBuffer result = new StringBuffer();
        try (Scanner scanner = new Scanner(contract.getFile())) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.append(line).append("\n");
            }

            scanner.close();

        } catch (IOException e) {
            LOG.error("error", e);
        }

        SolidityContract powerOfAttorney = bc.submitNewContract(result.toString());
        LOG.info("Calculating...");
        // Creates the contract call transaction, submits it to the blockchain
        // and generates a new block which includes this transaction
        // After new block is generated the contract state is changed
//        powerOfAttorney.callFunction("add", 100);
        // Check the contract state with a constant call which returns result
        // but doesn't generate any transactions and remain the contract state unchanged
//        assertEqual(BigInteger.valueOf(100), (BigInteger) powerOfAttorney.callConstFunction("result")[0]);
//        powerOfAttorney.callFunction("add", 200);
//        assertEqual(BigInteger.valueOf(300), (BigInteger) powerOfAttorney.callConstFunction("result")[0]);
//        powerOfAttorney.callFunction("mul", 10);
//        assertEqual(BigInteger.valueOf(3000), (BigInteger) powerOfAttorney.callConstFunction("result")[0]);
//        powerOfAttorney.callFunction("div", 5);
//        assertEqual(BigInteger.valueOf(600), (BigInteger) powerOfAttorney.callConstFunction("result")[0]);
        LOG.info("Clearing...");
        powerOfAttorney.callFunction("empower", bc.getSender().getAddress(), new byte[]{0124});
//        assertEqual(BigInteger.valueOf(0), (BigInteger) powerOfAttorney.callConstFunction("result")[0]);
        // We are done - the Solidity contract worked as expected.
        LOG.info("Done.");

    }
}
