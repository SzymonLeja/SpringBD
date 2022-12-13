package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.User;
import com.example.SpringDB.services.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("")
    public String addUser(@RequestBody User user) {
        return usersService.saveUser(user);
    }

    @DeleteMapping("")
    public String deleteUser(@RequestBody Integer userId) {
        return usersService.deleteUser(userId);
    }

}
