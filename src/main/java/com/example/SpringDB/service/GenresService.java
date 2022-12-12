package com.example.SpringDB.service;

import com.example.SpringDB.entity.Genre;


public interface GenresService {
    public String saveGenre(Genre genre);
    public String deleteGenre(Integer genreId);
}
