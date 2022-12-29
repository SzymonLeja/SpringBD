package com.example.SpringDB.services.Artists;

import com.example.SpringDB.entities.Artist;
import com.example.SpringDB.repositories.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
        if (!artistsRepository.findById(artistId).isEmpty()) {
            artistsRepository.deleteById(artistId);
            return "Artist removed";
        } else {
            return "Artist not found";
        }
    }

    @Override
    public List<Artist> getArtist(String artistName) {
        return artistsRepository.findByNameContaining(artistName);
    }

    @Override
    public String updateArtist(Artist artist) {
        if (!artistsRepository.findById(artist.getId_artist()).isEmpty()) {
            artistsRepository.findById(artist.getId_artist()).get().updateArtist(artist);
            return "Artist updated";
        } else {
            return "Artist not found";
        }
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistsRepository.findAll();
    }
}
