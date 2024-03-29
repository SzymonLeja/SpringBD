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
    @GetMapping(value="/{username}")
    public User getUser(@PathVariable("username") String username) {
        return usersService.getUser(username);
    }

    @CrossOrigin
    @GetMapping(value="/l/{username}")
    public String getUser(@PathVariable("username") String username, @RequestParam String password) {
        return usersService.getUser(username).getPassword().equals(password) ? "{" + usersService.getUser(username).getIdUser().toString() +"," + usersService.getUser(username).getUserName().toString()+ "," + usersService.getUser(username).getType().toString() + "}" : "false";
    }

    @CrossOrigin
    @PutMapping("")
    public String updateUser(@RequestBody User user) {
        return usersService.updateUser(user);
    }
}
