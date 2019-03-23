package ie.altech.demo.user;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import ie.altech.demo.user.exceptions.UserNotFoundException;
import org.apache.tomcat.util.http.parser.AcceptLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserService {

    @Autowired
    private MessageSource messageSource;

    private UserDaoService userDaoService = UserDaoService.getInstance();
    private UserHelper userHelper = UserHelper.getInstance();

    @GetMapping(path = "/user/{id}")
    public Resource<User> getUser(@PathVariable Integer id){
         User user = userDaoService.findUser(id);
        if (user == null) {
            String message = "Cant't find user.";
            throw new UserNotFoundException(message);
        }
        // "all-users", SERVER_PATH + "/users"
        // retrievAllUsers
        Resource<User> resource = new Resource<>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @GetMapping(path = "/user/users")
    public List<User> getAllUsers(){
        return userDaoService.findAll();
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody User user){
        User existedUser = userDaoService.findUser(id);
        if (existedUser != null) {
            existedUser.setDob(user.getDob());
            existedUser.setName(user.getName());
            return ResponseEntity.ok(existedUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity deleteUserById(@PathVariable Integer id){

        if (!userDaoService.deleteUserById(id)) {
            throw new UserNotFoundException("User not found");
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/message")
    public String getMessage(){
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
