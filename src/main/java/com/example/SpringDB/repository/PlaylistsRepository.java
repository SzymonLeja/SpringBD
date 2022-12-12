package com.example.SpringDB.repository;

import com.example.SpringDB.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface PlaylistsRepository extends JpaRepository<Playlist, Integer> {

}
