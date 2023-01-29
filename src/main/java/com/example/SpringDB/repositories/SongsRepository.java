package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface SongsRepository extends JpaRepository<Song, Integer> {
    Song findBySongName(String songName);

    List<Song> findBySongNameContaining(String songName);
    Song findByIdSong(Integer idSong);

    List<Song> findByAlbumArtistIdArtist(Integer albumId);
    List<Song> findByAlbumIdAlbum(Integer albumId);

}
