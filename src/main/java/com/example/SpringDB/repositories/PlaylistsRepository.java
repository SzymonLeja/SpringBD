package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface PlaylistsRepository extends JpaRepository<Playlist, Integer> {

}
