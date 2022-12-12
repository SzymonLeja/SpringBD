package com.example.SpringDB.repository;

import com.example.SpringDB.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AlbumsRepository extends JpaRepository<Album, Integer> {
}
