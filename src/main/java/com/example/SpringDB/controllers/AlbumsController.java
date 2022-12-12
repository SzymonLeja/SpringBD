package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Album;
import com.example.SpringDB.services.Albums.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/albums")
class AlbumsController {

    @Autowired
    private AlbumsService albumsService;

    @PostMapping("/add")
    public String addAlbum(@RequestBody Album album) {
        return albumsService.saveAlbum(album);
    }

    @PostMapping("/delete")
    public String deleteAlbum(@RequestBody Integer albumId) {
        return albumsService.deleteAlbum(albumId);
    }

}
