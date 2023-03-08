package com.mustafafindik.elasticsearchapp.Service;

import com.mustafafindik.elasticsearchapp.Entity.User;

import java.util.List;

public interface UserService {
    User getUser(String id);
    List<User> allUser();
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(String id);

}
