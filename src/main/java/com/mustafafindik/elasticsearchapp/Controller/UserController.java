package com.mustafafindik.elasticsearchapp.Controller;

import com.mustafafindik.elasticsearchapp.Entity.User;
import com.mustafafindik.elasticsearchapp.Repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostConstruct
    public void init(){
        User user = new User();
        user.setName("Mustafa");
        user.setSurname("Findik");
        user.setAddress("Turkey");
        user.setBirthDay(Calendar.getInstance().getTime());
        user.setId("1");
        userRepository.save(user);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<User>> getUser(@PathVariable("search")String search){
        List<User> user =  userRepository.getByCustomQuery(search);
        return ResponseEntity.ok(user);
    }

}
