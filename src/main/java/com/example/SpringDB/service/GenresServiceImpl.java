package com.example.SpringDB.service;

import com.example.SpringDB.entity.Genre;
import com.example.SpringDB.repository.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GenresServiceImpl implements GenresService {

    @Autowired
    private GenresRepository genresRepository;

    @Override
    public String saveGenre(Genre genre) {
        genresRepository.save(genre);
        return "Genre added";
    }

    @Override
    public String deleteGenre(Integer genreId) {
        if(genresRepository.existsById(genreId)) {
            genresRepository.deleteById(genreId);
            return "Genre removed";
        } else {
            return "There is no genre with id " + genreId;
        }
    }
}
