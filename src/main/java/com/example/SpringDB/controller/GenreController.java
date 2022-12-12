package com.example.SpringDB.controller;

import com.example.SpringDB.entity.Artist;
import com.example.SpringDB.entity.Genre;
import com.example.SpringDB.service.ArtistsService;
import com.example.SpringDB.service.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/genres")
@CrossOrigin
class GenreController {

        @Autowired
        private GenresService genresService;

        @PostMapping("/add")
        public String addGenre(@RequestBody Genre genre) {
            return genresService.saveGenre(genre);
        }

        @PostMapping("/delete")
        public String deleteGenre(@RequestBody Integer genreId) {
            return genresService.deleteGenre(genreId);
        }
}
