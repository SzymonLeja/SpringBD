package com.example.SpringDB.services.Genres;

import com.example.SpringDB.entities.Genre;

import java.util.List;


public interface GenresService {
    public String saveGenre(Genre genre);
    public String deleteGenre(Integer genreId);

    public String updateGenre(Genre genre);

    List<Genre> getGenre(String genreName);

}
