package com.example.SpringDB.service;

import com.example.SpringDB.entity.Song;

public interface SongsService {
    public String saveSong(Song song);
    public String deleteSong(Integer songId);
}
