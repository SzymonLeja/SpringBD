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
    @CrossOrigin
    @PostMapping("")
    public String addAlbum(@RequestBody Album album) {
        return albumsService.saveAlbum(album);
    }
    
    @CrossOrigin
    @DeleteMapping("")
    public String deleteAlbum(@RequestBody Integer albumId) {
        return albumsService.deleteAlbum(albumId);
    }

    @CrossOrigin
    @GetMapping("")
    public List<Album> getAlbum(@RequestBody String albumName) {
        return albumsService.getAlbum(albumName);
    }

    @CrossOrigin
    @PutMapping("")
    public String updateAlbum(@RequestBody Album album) {
        return albumsService.updateAlbum(album);
    }

    @CrossOrigin
    @GetMapping("/all")
    public List<Album> getAllAlbums() {
        return albumsService.getAlbumsRepository();
    }
}
