package com.example.SpringDB.controller;


import com.example.SpringDB.entity.Playlist;
import com.example.SpringDB.service.PlaylistsService;
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
