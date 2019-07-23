package com.recipes.service;

import com.recipes.model.User;

import java.util.List;

public interface UserService {
    void save(User user);
    List<User> findAll();
    User findOne(Long id);
    User findByUsername(String username);
}
