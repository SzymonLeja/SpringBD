package com.example.SpringDB.services.PlaylistsSongs;

import com.example.SpringDB.entities.PlaylistSong;

public interface PlaylistsSongsService {
    public String savePlaylistSong(PlaylistSong playlistSong);
    public String deletePlaylistSong(Integer playlistSongId);
}
