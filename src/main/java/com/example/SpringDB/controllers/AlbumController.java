package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Album;
import com.example.SpringDB.services.Albums.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/album")
class AlbumController {

    @Autowired
    private AlbumsService albumsService;

    @PostMapping("")
    public String addAlbum(@RequestBody Album album) {
        return albumsService.saveAlbum(album);
    }

    @DeleteMapping("")
    public String deleteAlbum(@RequestBody Integer albumId) {
        return albumsService.deleteAlbum(albumId);
    }

}
