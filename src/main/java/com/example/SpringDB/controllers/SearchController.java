package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Album;
import com.example.SpringDB.entities.Artist;
import com.example.SpringDB.entities.Song;
import com.example.SpringDB.services.Albums.AlbumsService;
import com.example.SpringDB.services.Artists.ArtistsService;
import com.example.SpringDB.services.Songs.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private AlbumsService albumsService;
    @Autowired
    private SongsService songsService;
    @Autowired

    private ArtistsService artistsService;

    @CrossOrigin
    @GetMapping("{query}")
    public List<List<Object>> search(@PathVariable String query) {
        List<Album> albums = albumsService.getAlbum(query);
        List<Song> songs = songsService.getSongsByName(query);
        List<Artist> artists = artistsService.getArtist(query);
        return List.of(List.of(albums, songs, artists));
    }


}
