package com.mustafafindik.elasticsearchapp.Service;

import com.mustafafindik.elasticsearchapp.Entity.User;
import com.mustafafindik.elasticsearchapp.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> allUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
       userRepository.deleteById(id);
    }
}
