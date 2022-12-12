package com.example.SpringDB.service;

import com.example.SpringDB.entity.Album;


public interface AlbumsService {
    public String saveAlbum(Album album);
    public String deleteAlbum(Integer albumId);
}
