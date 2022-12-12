package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlbumsRepository extends JpaRepository<Album, Integer> {
}
