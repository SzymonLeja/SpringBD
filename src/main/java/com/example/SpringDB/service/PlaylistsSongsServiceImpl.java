package com.example.SpringDB.service;

import com.example.SpringDB.entity.Playlist;
import com.example.SpringDB.entity.PlaylistSong;
import com.example.SpringDB.repository.PlaylistsRepository;
import com.example.SpringDB.repository.PlaylistsSongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistsSongsServiceImpl implements PlaylistsSongsService {
    @Autowired
    private PlaylistsSongsRepository playlistsSongsRepository;

    @Override
    public String savePlaylistSong(PlaylistSong playlistSong) {
        playlistsSongsRepository.save(playlistSong);
        return "Playlist added";
    }

    @Override
    public String deletePlaylistSong(Integer playlistSongId) {
        if(playlistsSongsRepository.existsById(playlistSongId)) {
            playlistsSongsRepository.deleteById(playlistSongId);
            return "Playlist removed";
        } else {
            return "There is no playlist with id " + playlistSongId;
        }
    }

}
