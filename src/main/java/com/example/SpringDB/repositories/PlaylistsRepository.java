package com.example.SpringDB.repositories;

import com.example.SpringDB.entities.Playlist;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public
interface PlaylistsRepository extends JpaRepository<Playlist, Integer> {

    //List<Playlist> findByUser_Id_user(Integer userId);
}
