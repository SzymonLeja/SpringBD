package com.example.SpringDB.services.Songs;

import com.example.SpringDB.entities.Song;

public interface SongsService {
    public String saveSong(Song song);
    public String deleteSong(Integer songId);
}
