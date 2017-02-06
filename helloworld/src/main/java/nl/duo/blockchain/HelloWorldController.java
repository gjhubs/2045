package nl.duo.blockchain;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by martijnvantiel on 06-02-17.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/helloworld")
    public String helloWorld(){

        return "Hello World";
    }

}
