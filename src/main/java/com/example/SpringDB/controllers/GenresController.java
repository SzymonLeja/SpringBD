package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Genre;
import com.example.SpringDB.services.Genres.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/genres")
@CrossOrigin
class GenresController {

        @Autowired
        private GenresService genresService;

        @PostMapping("")
        public String addGenre(@RequestBody Genre genre) {
            return genresService.saveGenre(genre);
        }

        @DeleteMapping("")
        public String deleteGenre(@RequestBody Integer genreId) {
            return genresService.deleteGenre(genreId);
        }
}
