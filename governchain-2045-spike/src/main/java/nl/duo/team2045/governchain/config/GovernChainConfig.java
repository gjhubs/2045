package nl.duo.team2045.governchain.config;

import org.ethereum.facade.Ethereum;
import org.ethereum.facade.EthereumFactory;
import org.ethereum.samples.BasicSample;
import org.ethereum.util.blockchain.StandaloneBlockchain;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * Created by geertjan on 11-2-17.
 */
@Configuration
public class GovernChainConfig {

    @Bean
    public Resource contract(){
        return new ClassPathResource("/config/power_of_attorney.sol");
    }

    
//    @Bean
//    public Ethereum ethereum(){
//        return EthereumFactory.createEthereum(GovernChainConfig.class);
//    }
}
