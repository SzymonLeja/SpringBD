package com.example.SpringDB.services.Songs;

import com.example.SpringDB.entities.Song;
import com.example.SpringDB.repositories.SongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public String updateSong(Song song) {
        if(songsRepository.existsById(song.getId_song())) {
            songsRepository.save(song);
            return "Playlist updated";
        } else {
            return "There is no playlist with id " + song.getId_song();
        }
    }

    @Override
    public List<Song> getSong(String songTitle) {
        return songsRepository.findBySongNameContaining(songTitle);
    }

    public List<Song> getSongsRepository() {
        return songsRepository.count() > 0 ? songsRepository.findAll() : null;
    }
}
