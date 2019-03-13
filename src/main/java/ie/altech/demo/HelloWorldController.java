package ie.altech.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {

    // GET
    // URI - /message
    // response - Hello Worlds
    @GetMapping(path = "/message")
    public String getMessage(){
        return "Hello World!";
    }
}
