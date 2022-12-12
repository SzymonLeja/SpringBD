package com.example.SpringDB.service;

import com.example.SpringDB.entity.Artist;
import jakarta.persistence.Id;


public interface ArtistsService {
    public Artist saveArtist(Artist artist);
    public String deleteArtist(Integer artistId);

}
