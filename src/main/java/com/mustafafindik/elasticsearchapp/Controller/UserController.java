package com.mustafafindik.elasticsearchapp.Controller;

import com.mustafafindik.elasticsearchapp.Entity.User;
import com.mustafafindik.elasticsearchapp.Repository.UserRepository;
import com.mustafafindik.elasticsearchapp.Service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    public UserController(UserRepository userRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @PostConstruct
    public void init(){
        User user = new User();
        user.setName("Mustafa");
        user.setSurname("Findik");
        user.setAddress("Turkey");
        user.setBirthday("21-05-1999");
        user.setId("1");
        userRepository.save(user);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<User>> getUser(@PathVariable("search")String search){
      //  List<User> user =  userRepository.getByCustomQuery(search);
        List<User> user = userRepository.findByNameLikeOrSurnameLike(search,search);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping
    public void deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
    }
    @PutMapping
    public void updateUser(@RequestBody User user) {
        userService.saveUser(user);
    }
    @GetMapping
    public void allUsers(){
        userService.allUser();
    }
}
