package ie.altech.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {

    // GET
    // URI - /message
    // response - Hello Worlds
    @GetMapping(path = "/message")
    public String getMessage() {
        return "Rest controller test. If you can see this message, then controller is working fine.";
    }

    // hello-world-bean
    // URI - /message
    // response - Hello Worlds
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Rest controller test. If you can see this message, then controller is working fine.");
    }

    // request with path variable
    @GetMapping(path = "/message/name/{name}")
    public HelloWorldBean getMessage(@PathVariable String name) {
        return new HelloWorldBean(String.format("This message is addressed to %s", name));
    }
}