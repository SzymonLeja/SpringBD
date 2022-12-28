package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Song;
import com.example.SpringDB.services.Songs.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
@CrossOrigin
public class SongController {

    @Autowired
    private SongsService songsService;

    @PostMapping("")
    public String addSong(@RequestBody Song song) {
        return songsService.saveSong(song);
    }

    @GetMapping("")
    public Song getSong(@RequestBody Integer songId) {
        return songsService.getSong(songId);
    }

    @PutMapping("")
    public String updateSong(@RequestBody Song song) {
        return songsService.updateSong(song);
    }

    @DeleteMapping("")
    public String deletePlaylistSong(@RequestBody Integer playlistId) {
        return songsService.deleteSong(playlistId);
    }






}
