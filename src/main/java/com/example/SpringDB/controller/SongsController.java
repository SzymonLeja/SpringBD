package com.example.SpringDB.controller;


import com.example.SpringDB.entity.PlaylistSong;
import com.example.SpringDB.entity.Song;
import com.example.SpringDB.service.PlaylistsSongsService;
import com.example.SpringDB.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongsController {

    @Autowired
    private SongsService songsService;

    @PostMapping("/add")
    public String addSong(@RequestBody Song song) {
        return songsService.saveSong(song);
    }

    @PostMapping("/delete")
    public String deletePlaylistSong(@RequestBody Integer playlistId) {
        return songsService.deleteSong(playlistId);
    }




}
