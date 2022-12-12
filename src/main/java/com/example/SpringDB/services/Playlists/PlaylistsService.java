package com.example.SpringDB.services.Playlists;

import com.example.SpringDB.entities.Playlist;

public interface PlaylistsService {
    public String savePlaylist(Playlist playlist);
    public String deletePlaylist(Integer playlistId);
}
