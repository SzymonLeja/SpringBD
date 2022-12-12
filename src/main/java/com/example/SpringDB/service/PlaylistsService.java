package com.example.SpringDB.service;

import com.example.SpringDB.entity.Playlist;

public interface PlaylistsService {
    public String savePlaylist(Playlist playlist);
    public String deletePlaylist(Integer playlistId);
}
