package ie.altech.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping(path = "/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Andrej Lavrinovic");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Andrej", "Lavrinovic"));
    }
}
