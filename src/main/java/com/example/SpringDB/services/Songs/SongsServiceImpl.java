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
        if(songsRepository.existsById(song.getIdSong())) {
            songsRepository.save(song);
            return "Playlist updated";
        } else {
            return "There is no playlist with id " + song.getIdSong();
        }
    }

    @Override
    public Song getSong(Integer songId) {
        return songsRepository.findByIdSong(songId);
    }

    @Override
    public Song getSongByName(String songName) {
        return songsRepository.findBySongName(songName);
    }
    @Override
    public List<Song> getSongsByName(String songName) {
        return songsRepository.findBySongNameContaining(songName);
    }
    @Override
    public List<Song> getSongsByAlbum(Integer albumId) {
        return songsRepository.findByAlbumIdAlbum(albumId);
    }

    @Override
    public List<Song> getSongsByArtist(Integer artistId) {
        return songsRepository.findByAlbumArtistIdArtist(artistId);
    }
    public List<Song> getSongsRepository() {
        return songsRepository.count() > 0 ? songsRepository.findAll() : null;
    }
}
