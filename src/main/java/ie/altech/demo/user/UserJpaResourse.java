package ie.altech.demo.user;

import ie.altech.demo.post.Post;
import ie.altech.demo.post.PostRepository;
import ie.altech.demo.user.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJpaResourse {

    @Autowired
    private MessageSource messageSource;

    private UserDaoService userDaoService = UserDaoService.getInstance();

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> getUser(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            String message = "Cant't find user.";
            throw new UserNotFoundException(message);
        } else {
            // "all-users", SERVER_PATH + "/users"
            // retrievAllUsers
            Resource<User> resource = new Resource<>(user.get());
            ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
            resource.add(linkTo.withRel("all-users"));
            return resource;
        }
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){

        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(path = "/jpa/users/{id}")
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

    @DeleteMapping(path = "/jpa/users/{id}")
    public ResponseEntity deleteUserById(@PathVariable Integer id){

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<User> retrieveAllPostsPerUaer(@PathVariable int id){
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id : " + id);
        }

        return ResponseEntity.ok(userOptional.get());
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
    public ResponseEntity<User> createPostsForUser(@PathVariable int id, @RequestBody Post post){

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id : " + id);
        }

        User user = userOptional.get();
        post.setUser(user);

        postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }
}
