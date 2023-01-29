package com.example.SpringDB.services.PlaylistsSongs;

import com.example.SpringDB.entities.PlaylistSong;
import com.example.SpringDB.repositories.PlaylistsSongsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PlaylistsSongsServiceImpl implements PlaylistsSongsService {
    @Autowired
    private PlaylistsSongsRepository playlistsSongsRepository;

    @Override
    public String savePlaylistSong(PlaylistSong playlistSong) {
        AtomicReference<String> result = new AtomicReference<>("PlaylistSong saved");
        playlistsSongsRepository.findBySongIdSongAndPlaylistIdPlaylist(playlistSong.getSong().getIdSong(), playlistSong.getPlaylist().getIdPlaylist()).ifPresentOrElse(
                (playlistSong1) -> {
                    result.set("PlaylistSong already exists");
                    throw new RuntimeException("Song already in playlist");
                },
                () -> {
                    playlistsSongsRepository.save(playlistSong);
                }
        );
        return result.get();
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

    @Override
    public List<PlaylistSong> getPlaylistSongs(Integer playlistId) {
        return playlistsSongsRepository.findByPlaylistIdPlaylist(playlistId);
    }

    @Override
    public String updatePlaylistSong(PlaylistSong playlistSong) {
        if(playlistsSongsRepository.existsById(playlistSong.getIdPlaylistSong())) {
            playlistsSongsRepository.save(playlistSong);
            return "Playlist updated";
        } else {
            return "There is no playlist with id " + playlistSong.getIdPlaylistSong();
        }
    }

}
