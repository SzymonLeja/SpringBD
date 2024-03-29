package com.example.SpringDB.services.Genres;

import com.example.SpringDB.entities.Genre;
import com.example.SpringDB.repositories.GenresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public String updateGenre(Genre genre) {
        if(genresRepository.existsById(genre.getIdGenre())) {
            genresRepository.save(genre);
            return "Genre updated";
        } else {
            return "There is no genre with id " + genre.getIdGenre();
        }
    }

    @Override
    public List<Genre> getGenresRepository() {
        return genresRepository.count() > 0 ? genresRepository.findAll() : null;
    }
    @Override
    public List<Genre> getGenre(String genreName) {
        return genresRepository.findByNameContaining(genreName);
    }
}
