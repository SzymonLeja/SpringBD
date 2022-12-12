package com.example.SpringDB.service;

import com.example.SpringDB.entity.Playlist;
import com.example.SpringDB.entity.PlaylistSong;

public interface PlaylistsSongsService {
    public String savePlaylistSong(PlaylistSong playlistSong);
    public String deletePlaylistSong(Integer playlistSongId);
}
