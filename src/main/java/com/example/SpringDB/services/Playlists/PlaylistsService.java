package com.example.SpringDB.services.Playlists;

import com.example.SpringDB.entities.Playlist;

import java.util.List;

public interface PlaylistsService {
    public String savePlaylist(Playlist playlist);
    public String deletePlaylist(Integer playlistId);

    public String updatePlaylist(Playlist playlist);

    public List<Playlist> getPlaylist(String playlistName);
}
