package com.example.SpringDB.repository;

import com.example.SpringDB.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface PlaylistsRepository extends JpaRepository<Playlist, Integer> {

}

@Repository
interface SongsRepository extends JpaRepository<Song, Integer> {

}

@Repository
interface UsersRepository extends JpaRepository<User, Integer> {

}

@Repository
interface Playlists_SongsRepository extends JpaRepository<Playlist_Song, Integer> {

}

