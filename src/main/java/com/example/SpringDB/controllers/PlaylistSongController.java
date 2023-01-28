package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.PlaylistSong;
import com.example.SpringDB.services.PlaylistsSongs.PlaylistsSongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists/song")
@CrossOrigin
public class PlaylistSongController {

    @Autowired
    private PlaylistsSongsService playlistsSongsService;

    @PostMapping("")
    public String addPlaylistSong(@RequestBody PlaylistSong playlistSong) {
        return playlistsSongsService.savePlaylistSong(playlistSong);
    }

    @DeleteMapping("")
    public String deletePlaylistSong(@RequestBody Integer playlistSongId) {
        return playlistsSongsService.deletePlaylistSong(playlistSongId);
    }

    @CrossOrigin
    @GetMapping("/{playlistId}")
    public List<PlaylistSong> getPlaylistSong(@PathVariable String playlistId) {
        return playlistsSongsService.getPlaylistSongs(Integer.parseInt(playlistId));
    }

    @PutMapping("")
    public String updatePlaylistSong(@RequestBody PlaylistSong playlistSong) {
        return playlistsSongsService.updatePlaylistSong(playlistSong);
    }
}
