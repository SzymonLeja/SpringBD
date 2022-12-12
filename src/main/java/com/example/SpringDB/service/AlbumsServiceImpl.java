package com.example.SpringDB.service;

import com.example.SpringDB.entity.Album;
import com.example.SpringDB.repository.AlbumsRepository;
import com.example.SpringDB.repository.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    private AlbumsRepository albumsRepository;
    @Autowired
    private ArtistsRepository artistsRepository;

    @Override
    public String saveAlbum(Album album) {

        if(!artistsRepository.findById(album.getArtist().getId_artist()).isPresent()) {
            return "There is no artist with id " + album.getArtist().getId_artist();
        } else {
            albumsRepository.save(album);
            return "Album added";
        }
    }

    @Override
    public String deleteAlbum(Integer albumId) {
        if(!albumsRepository.findById(albumId).isPresent()) {
            return "There is no album with id " + albumId;
        } else {
            albumsRepository.deleteById(albumId);
            return "Album removed";
        }
    }
}
