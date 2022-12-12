package com.example.SpringDB.service;

import com.example.SpringDB.entity.Playlist;
import com.example.SpringDB.repository.PlaylistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistsServiceImpl implements PlaylistsService {
    @Autowired
    private PlaylistsRepository playlistsRepository;

    @Override
    public String savePlaylist(Playlist playlist) {
        playlistsRepository.save(playlist);
        return "Playlist added";
    }

    @Override
    public String deletePlaylist(Integer playlistId) {
        if(playlistsRepository.existsById(playlistId)) {
            playlistsRepository.deleteById(playlistId);
            return "Playlist removed";
        } else {
            return "There is no playlist with id " + playlistId;
        }
    }

}
