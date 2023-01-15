package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public
interface UsersRepository extends JpaRepository<User, Integer> {
    User findByUserName(String userName);

}

