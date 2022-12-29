package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Song;
import com.example.SpringDB.services.Songs.SongsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongController {

    @Autowired
    private SongsService songsService;

    @PostMapping("")
    public String addSong(@RequestBody Song song) {
        return songsService.saveSong(song);
    }

    @GetMapping("")
    public List<Song> getSong(@RequestBody String songName) {
        return songsService.getSong(songName);
    }

    @PutMapping("")
    public String updateSong(@RequestBody Song song) {
        return songsService.updateSong(song);
    }

    @DeleteMapping("")
    public String deletePlaylistSong(@RequestBody Integer playlistId) {
        return songsService.deleteSong(playlistId);
    }

    @GetMapping("/all")
    public List<Song> getAllSongs() {
        return songsService.getSongsRepository();
    }
}
