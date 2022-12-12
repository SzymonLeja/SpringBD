package com.example.SpringDB.services.Genres;

import com.example.SpringDB.entities.Genre;


public interface GenresService {
    public String saveGenre(Genre genre);
    public String deleteGenre(Integer genreId);
}
