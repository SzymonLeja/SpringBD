package com.example.SpringDB.services.Albums;

import com.example.SpringDB.entities.Album;

import java.util.List;


public interface AlbumsService {
    public String saveAlbum(Album album);
    public String deleteAlbum(Integer albumId);
    public List<Album> getAlbum(String albumName);
    public String updateAlbum(Album album);

    List<Album> getAlbumsRepository();
}
