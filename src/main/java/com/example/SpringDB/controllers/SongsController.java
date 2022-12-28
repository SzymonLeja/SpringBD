package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Song;
import com.example.SpringDB.services.Songs.SongsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongsController {

    @Autowired
    private SongsService songsService;

    @GetMapping("")
    public String getSongs(@RequestBody ObjectNode jsonNodes) {
        return songsService.getSongsRepository().toString();
    }
}
