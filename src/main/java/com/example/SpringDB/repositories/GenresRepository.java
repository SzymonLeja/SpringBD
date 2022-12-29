package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface GenresRepository extends JpaRepository<Genre, Integer> {

    List<Genre> findByNameContaining(String genreName);
}
