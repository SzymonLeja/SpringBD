package com.example.SpringDB.service;

import com.example.SpringDB.entity.Song;
import com.example.SpringDB.entity.User;

public interface UsersService {
    public String saveUser(User user);
    public String deleteUser(Integer userId);
}
