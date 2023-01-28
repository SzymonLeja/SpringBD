package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface PlaylistsSongsRepository extends JpaRepository<PlaylistSong, Integer> {

    List<PlaylistSong> findByPlaylistIdPlaylist(Integer playlistId);
}
