package com.example.SpringDB.services.Genres;

import com.example.SpringDB.entities.Genre;

import java.util.List;


public interface GenresService {
    public String saveGenre(Genre genre);
    public String deleteGenre(Integer genreId);

    public String updateGenre(Genre genre);

    public List<Genre> getGenresRepository();
    List<Genre> getGenre(String genreName);

}
