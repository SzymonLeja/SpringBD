package com.example.SpringDB.entities;

import java.sql.Time;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSong;
    private String songURL;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "idAlbum")
    private Album album;

    private String songName;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "idGenre")
    private Genre genre;
    private Time time;
    private Integer size;

    @JsonProperty("idAlbum") private void unpackNested(Integer albumId) {
        this.album = new Album();
        album.setIdAlbum(albumId);
    }
    @JsonProperty("idGenre") private void unpackNested2(Integer genreId) {
        this.genre = new Genre();
        genre.setIdGenre(genreId);
    }

    @Override
    public String toString() {
        return  "\n{\nid: " + idSong + ",\nsongName: " + songName + ",\nalbum: " + album.getAlbum_name() + ",\ngenre: " + genre.getGenre_name() + ",\ntime: " + time + ",\nsize: " + size + "\n}\n";
    }

    public String getSongURL(){
        return songURL;
    }
}
