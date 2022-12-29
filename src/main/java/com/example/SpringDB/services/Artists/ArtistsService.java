package com.example.SpringDB.services.Artists;

import com.example.SpringDB.entities.Artist;

import java.util.List;


public interface ArtistsService {
    public Artist saveArtist(Artist artist);
    public String deleteArtist(Integer artistId);

    public List<Artist> getArtist(String artistName);

    public String updateArtist(Artist artist);

    List<Artist> getAllArtists();
}
