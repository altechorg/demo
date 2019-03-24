package ie.altech.demo.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    // URL versioning
    @GetMapping(path = "/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Andrej Lavrinovic");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new Name("Andrej", "Lavrinovic"));
    }

    // Request Parameter versioning
    @GetMapping(path = "/person/param", params = "version=1")
    public PersonV1 getPersonByParameterV1(){
        return new PersonV1("Andrej Lavrinovic");
    }

    @GetMapping(path = "/person/param", params = "version=2")
    public PersonV2 getPersonByParameterV2(){
        return new PersonV2(new Name("Andrej", "Lavrinovic"));
    }

    // Header versioning
    @GetMapping(path = "/person/header", headers = "X-Person-Version=1")
    public PersonV1 getPersonByHeaderV1(){
        return new PersonV1("Andrej Lavrinovic");
    }

    @GetMapping(path = "/person/header", headers = "X-Person-Version=2")
    public PersonV2 getPersonByHeaderV2(){
        return new PersonV2(new Name("Andrej", "Lavrinovic"));
    }

    // MIME type versioning
    @GetMapping(path = "/person/produces", produces = "application/ie.altech.app-v1+json")
    public PersonV1 getPersonByProducesV1(){
        return new PersonV1("Andrej Lavrinovic");
    }

    @GetMapping(path = "/person/produces", produces = "application/ie.altech.app-v2+json")
    public PersonV2 getPersonByProducesV2(){
        return new PersonV2(new Name("Andrej", "Lavrinovic"));
    }
}
