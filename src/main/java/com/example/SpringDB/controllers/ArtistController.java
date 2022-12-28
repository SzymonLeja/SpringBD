package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Artist;
import com.example.SpringDB.services.Artists.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/artist")
@CrossOrigin
class ArtistController {

        @Autowired
        private ArtistsService artistsService;

        @PostMapping("")
        public String addArtist(@RequestBody Artist artist) {
            artistsService.saveArtist(artist);
            return "New artist added";
        }

        @DeleteMapping("")
        public String removeArtist(@RequestBody Integer artistId) {
            return artistsService.deleteArtist(artistId);
        }
}
