package com.example.SpringDB.services.Songs;

import com.example.SpringDB.entities.Song;
import com.example.SpringDB.repositories.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongsServiceImpl implements SongsService {
    @Autowired
    private SongsRepository songsRepository;

    @Override
    public String saveSong(Song song) {
        songsRepository.save(song);
        return "Playlist added";
    }
    @Override
    public String deleteSong(Integer songId) {
        if(songsRepository.existsById(songId)) {
            songsRepository.deleteById(songId);
            return "Playlist removed";
        } else {
            return "There is no playlist with id " + songId;
        }
    }

}
