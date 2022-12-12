package com.example.SpringDB.service;

import com.example.SpringDB.SpringDbApplication;
import com.example.SpringDB.entity.Artist;
import com.example.SpringDB.repository.ArtistsRepository;
import jakarta.persistence.Id;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class ArtistsServiceImpl implements ArtistsService {

    @Autowired
    private ArtistsRepository artistsRepository;

    @Override
    public Artist saveArtist(Artist artist) {
        return artistsRepository.save(artist);
    }
    @Override
    public String deleteArtist(Integer artistId) {
        if(!artistsRepository.findById(artistId).isEmpty()) {
            artistsRepository.deleteById(artistId);
            return "Artist removed";
        } else {
            return "Artist not found";
        }
    }
}
