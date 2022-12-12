package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Playlist;
import com.example.SpringDB.services.Playlists.PlaylistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistsController {

    @Autowired
    private PlaylistsService playlistsService;

    @PostMapping("/add")
    public String addPlaylist(@RequestBody Playlist playlist) {
        return playlistsService.savePlaylist(playlist);
    }

    @PostMapping("/delete")
    public String deletePlaylist(@RequestBody Integer playlistId) {
        return playlistsService.deletePlaylist(playlistId);
    }




}
