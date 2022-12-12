package com.example.SpringDB.services.Artists;

import com.example.SpringDB.entities.Artist;


public interface ArtistsService {
    public Artist saveArtist(Artist artist);
    public String deleteArtist(Integer artistId);

}
