package com.example.SpringDB.services.PlaylistsSongs;

import com.example.SpringDB.entities.PlaylistSong;

import java.util.List;

public interface PlaylistsSongsService {
    public String savePlaylistSong(PlaylistSong playlistSong);
    public String deletePlaylistSong(Integer playlistSongId);

    List<PlaylistSong> getPlaylistSongs(Integer playlistId);

    String updatePlaylistSong(PlaylistSong playlistSong);
}
