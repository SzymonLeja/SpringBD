package com.example.SpringDB.services.Albums;

import com.example.SpringDB.entities.Album;


public interface AlbumsService {
    public String saveAlbum(Album album);
    public String deleteAlbum(Integer albumId);
}
