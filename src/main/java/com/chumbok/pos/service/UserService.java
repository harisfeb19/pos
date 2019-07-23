package com.chumbok.pos.service;

import java.util.List;

import com.chumbok.pos.entity.User;

public interface UserService {
    public User findUserByEmail(String email);

    public void saveUser(User user);
    
    public List<User> getAllUsers();
}