package com.example.SpringDB.services.Songs;

import com.example.SpringDB.entities.Song;

import java.util.List;

public interface SongsService {
    public String saveSong(Song song);
    public String deleteSong(Integer songId);
    public String updateSong(Song song);
    public Song getSong(Integer songId);

    public Song getSongByName(String songName);
    public List<Song> getSongsByName(String songName);
    public List<Song> getSongsByAlbum(Integer albumId);

    public List<Song> getSongsByArtist(Integer artistId);

    public List<Song> getSongsRepository();
}
