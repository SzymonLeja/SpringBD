package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Playlist;
import com.example.SpringDB.services.Playlists.PlaylistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistController {

    @Autowired
    private PlaylistsService playlistsService;

    @PostMapping("")
    public String addPlaylist(@RequestBody Playlist playlist) {
        return playlistsService.savePlaylist(playlist);
    }

    @DeleteMapping("")
    public String deletePlaylist(@RequestBody Integer playlistId) {
        return playlistsService.deletePlaylist(playlistId);
    }

    @CrossOrigin
    @GetMapping("/{userId}")
    @ResponseBody
    public List<Playlist> getPlaylist(@PathVariable String userId) {
        return playlistsService.getPlaylistsByUserId(151);
    }

    @PutMapping("")
    public String updatePlaylist(@RequestBody Playlist playlist) {
        return playlistsService.updatePlaylist(playlist);
    }
}
