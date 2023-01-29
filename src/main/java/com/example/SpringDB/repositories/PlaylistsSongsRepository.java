package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.PlaylistSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public
interface PlaylistsSongsRepository extends JpaRepository<PlaylistSong, Integer> {

    List<PlaylistSong> findByPlaylistIdPlaylist(Integer playlistId);

    Optional<Object> findBySongIdSongAndPlaylistIdPlaylist(Integer idSong, Integer idPlaylist);
}
