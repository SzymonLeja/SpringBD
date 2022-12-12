package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.PlaylistSong;
import com.example.SpringDB.services.PlaylistsSongs.PlaylistsSongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playlists/songs")
@CrossOrigin
public class PlaylistsSongsController {

    @Autowired
    private PlaylistsSongsService playlistsSongsService;

    @PostMapping("/add")
    public String addPlaylistSong(@RequestBody PlaylistSong playlistSong) {
        return playlistsSongsService.savePlaylistSong(playlistSong);
    }

    @PostMapping("/delete")
    public String deletePlaylistSong(@RequestBody Integer playlistSongId) {
        return playlistsSongsService.deletePlaylistSong(playlistSongId);
    }




}
