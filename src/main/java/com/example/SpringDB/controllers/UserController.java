package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.User;
import com.example.SpringDB.services.Users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UsersService usersService;

    @CrossOrigin
    @PostMapping("")
    public String addUser(@RequestBody User user) {
        return usersService.saveUser(user);
    }

    @CrossOrigin
    @DeleteMapping("")
    public String deleteUser(@RequestBody Integer userId) {
        return usersService.deleteUser(userId);
    }

    @CrossOrigin
    @GetMapping("")
    public String getUser(@RequestBody String test) {
        return test;
    }

    @CrossOrigin
    @PutMapping("")
    public String updateUser(@RequestBody User user) {
        return usersService.updateUser(user);
    }
}
