package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ArtistsRepository extends JpaRepository<Artist, Integer> {

}
