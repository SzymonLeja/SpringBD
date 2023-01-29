package com.example.SpringDB.services.Albums;

import com.example.SpringDB.entities.Album;
import com.example.SpringDB.repositories.AlbumsRepository;
import com.example.SpringDB.repositories.ArtistsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Autowired
    private AlbumsRepository albumsRepository;
    @Autowired
    private ArtistsRepository artistsRepository;

    @Override
    public String saveAlbum(Album album) {

        if(!artistsRepository.findById(album.getArtist().getIdArtist()).isPresent()) {
            return "There is no artist with id " + album.getArtist().getIdArtist();
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

    @Override
    public List<Album> getAlbum(String albumName) {
        return albumsRepository.findByTitleContaining(albumName);
    }

    @Override
    public List<Album> getAlbumsByArtistId(Integer artistId) {
        return albumsRepository.findByArtistIdArtist(artistId);
    }
    @Override
    public String updateAlbum(Album album) {
        if(!albumsRepository.findById(album.getIdAlbum()).isPresent()) {
            return "There is no album with id " + album.getIdAlbum();
        } else {
            albumsRepository.save(album);
            return "Album updated";
        }
    }

    @Override
    public Album getAlbumById(Integer albumId) {
        return albumsRepository.findByIdAlbum(albumId);
    }

    @Override
    public List<Album> getAlbumsRepository() {
        return albumsRepository.findAll();
    }

}
