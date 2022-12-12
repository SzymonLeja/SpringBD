package com.example.SpringDB.repository;

import com.example.SpringDB.entity.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface PlaylistsSongsRepository extends JpaRepository<PlaylistSong, Integer> {

}
