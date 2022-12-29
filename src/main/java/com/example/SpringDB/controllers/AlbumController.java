package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Album;
import com.example.SpringDB.services.Albums.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
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

    @GetMapping("")
    public List<Album> getAlbum(@RequestBody String albumName) {
        return albumsService.getAlbum(albumName);
    }

    @PutMapping("")
    public String updateAlbum(@RequestBody Album album) {
        return albumsService.updateAlbum(album);
    }

    @GetMapping("/all")
    public String getAllAlbums() {
        return albumsService.getAlbumsRepository().toString();
    }
}
