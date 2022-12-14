package com.example.SpringDB.services.Songs;

import com.example.SpringDB.entities.Song;

import java.util.List;

public interface SongsService {
    public String saveSong(Song song);
    public String deleteSong(Integer songId);
    public String updateSong(Song song);
    public List<Song> getSong(String songName);
    public List<Song> getSongsRepository();
}
