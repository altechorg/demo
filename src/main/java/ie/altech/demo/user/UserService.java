package ie.altech.demo.user;

import ie.altech.demo.user.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserService {

    private UserDaoService userDaoService = UserDaoService.getInstance();
    private UserHelper userHelper = UserHelper.getInstance();

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
         User user = userDaoService.findUser(id);
        if (user == null) {
            String message = "Cant't find user.";
            throw new UserNotFoundException(message);
        }
        return ResponseEntity.ok(user);
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

}
