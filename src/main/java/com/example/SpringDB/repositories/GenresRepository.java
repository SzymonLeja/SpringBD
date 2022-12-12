package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface GenresRepository extends JpaRepository<Genre, Integer> {

}
