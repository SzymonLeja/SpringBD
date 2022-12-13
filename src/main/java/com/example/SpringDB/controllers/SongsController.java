package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Song;
import com.example.SpringDB.services.Songs.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongsController {

    @Autowired
    private SongsService songsService;

    @PostMapping("")
    public String addSong(@RequestBody Song song) {
        return songsService.saveSong(song);
    }

    @DeleteMapping("")
    public String deletePlaylistSong(@RequestBody Integer playlistId) {
        return songsService.deleteSong(playlistId);
    }




}
