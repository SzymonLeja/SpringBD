package com.example.SpringDB.controller;

import com.example.SpringDB.entity.User;
import com.example.SpringDB.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        return usersService.saveUser(user);
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody Integer userId) {
        return usersService.deleteUser(userId);
    }

}
