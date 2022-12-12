package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface PlaylistsSongsRepository extends JpaRepository<PlaylistSong, Integer> {

}
