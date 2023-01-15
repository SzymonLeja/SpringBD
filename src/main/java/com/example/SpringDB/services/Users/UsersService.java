package com.example.SpringDB.services.Users;

import com.example.SpringDB.entities.User;

public interface UsersService {
    public String saveUser(User user);
    public String deleteUser(Integer userId);
    public User getUser(String userName);
    public String updateUser(User user);
}
