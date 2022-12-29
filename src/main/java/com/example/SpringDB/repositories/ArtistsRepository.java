package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface ArtistsRepository extends JpaRepository<Artist, Integer> {

    List<Artist> findByNameContaining(String artistName);
}
