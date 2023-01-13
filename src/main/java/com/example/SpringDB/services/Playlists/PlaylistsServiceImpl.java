package com.example.SpringDB.services.Playlists;

import com.example.SpringDB.entities.Playlist;
import com.example.SpringDB.repositories.PlaylistsRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public String updatePlaylist(Playlist playlist) {
        if(playlistsRepository.existsById(playlist.getId_playlist())) {
            playlistsRepository.save(playlist);
            return "Playlist updated";
        } else {
            return "There is no playlist with id " + playlist.getId_playlist();
        }
    }

    @Override
    public List<Playlist> getPlaylist(Integer userId) {
        return playlistsRepository.findByUser_Id_user(userId);
    }

}
