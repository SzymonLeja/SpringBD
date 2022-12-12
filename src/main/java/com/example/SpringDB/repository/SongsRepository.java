package com.example.SpringDB.repository;

import com.example.SpringDB.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface SongsRepository extends JpaRepository<Song, Integer> {

}
