package com.example.SpringDB.repository;

import com.example.SpringDB.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface GenresRepository extends JpaRepository<Genre, Integer> {

}
