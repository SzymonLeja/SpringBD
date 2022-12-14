package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface SongsRepository extends JpaRepository<Song, Integer> {
    List<Song> findBySongNameContaining(String songName);

}
