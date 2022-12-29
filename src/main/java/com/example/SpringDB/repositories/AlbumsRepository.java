package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumsRepository extends JpaRepository<Album, Integer> {
    List<Album> findByTitleContaining(String albumName);
}
