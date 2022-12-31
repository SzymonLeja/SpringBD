package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Artist;
import com.example.SpringDB.services.Artists.ArtistsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/artists")
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
        public String removeArtist(@RequestBody String artistId) {
            return artistsService.deleteArtist(Integer.parseInt(artistId));
        }

        @GetMapping("")
        public List<Artist> getArtist(String artistName) {
            return artistsService.getArtist(artistName);
        }

        @PutMapping("")
        public String updateArtist(@RequestBody Artist artist) {
            return artistsService.updateArtist(artist);
        }

        @GetMapping("/all")
        public List<Artist> getAllArtists() {
            return artistsService.getAllArtists();
        }
}
