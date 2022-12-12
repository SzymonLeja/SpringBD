package com.example.SpringDB.repository;

import com.example.SpringDB.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface ArtistsRepository extends JpaRepository<Artist, Integer> {

}
