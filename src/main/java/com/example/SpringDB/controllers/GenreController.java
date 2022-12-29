package com.example.SpringDB.controllers;

import com.example.SpringDB.entities.Genre;
import com.example.SpringDB.services.Genres.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/genres")
@CrossOrigin
class GenreController {

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

        @GetMapping("")
        public List<Genre> getGenre(@RequestBody String genreName) {
            return genresService.getGenre(genreName);
        }

        @PutMapping("")
        public String updateGenre(@RequestBody Genre genre) {
            return genresService.updateGenre(genre);
        }
}
