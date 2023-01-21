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
    private Integer id_song;
    private String songURL;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_album")
    private Album album;

    private String songName;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_genre")
    private Genre genre;
    private Time time;
    private Integer size;

    @JsonProperty("id_album") private void unpackNested(Integer albumId) {
        this.album = new Album();
        album.setId_album(albumId);
    }
    @JsonProperty("id_genre") private void unpackNested2(Integer genreId) {
        this.genre = new Genre();
        genre.setId_genre(genreId);
    }

    @Override
    public String toString() {
        return  "\n{\nid: " + id_song + ",\nsongName: " + songName + ",\nalbum: " + album.getAlbum_name() + ",\ngenre: " + genre.getGenre_name() + ",\ntime: " + time + ",\nsize: " + size + "\n}\n";
    }

    public String getSongURL(){
        return songURL;
    }
}
