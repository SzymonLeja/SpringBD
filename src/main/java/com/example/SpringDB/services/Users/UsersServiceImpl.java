package com.example.SpringDB.services.Users;

import com.example.SpringDB.entities.User;
import com.example.SpringDB.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String saveUser(User user) {
        usersRepository.save(user);
        return "User added";
    }

    @Override
    public String deleteUser(Integer userId) {
        if(usersRepository.existsById(userId)) {
            usersRepository.deleteById(userId);
            return "User removed";
        } else {
            return "There is no user with id " + userId;
        }
    }

    @Override
    public User getUser(Integer userId) {
        return usersRepository.findById(userId).get();
    }

    @Override
    public String updateUser(User user) {
        if(usersRepository.findById(user.getId_user()).isPresent()){
            usersRepository.save(user);
            return "User updated";
        } else {
            return "There is no user with id " + user.getId_user();
        }
    }



}
