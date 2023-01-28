package com.example.SpringDB.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "albums")

public class Album {
    @Id
    @Column(name = "idAlbum")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlbum;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "idArtist")
    private Artist artist;
    @Column(name = "title")
    private String title;


    @JsonProperty("idArtist") private void unpackNested(Integer artistId) {
        this.artist = new Artist();
        artist.setIdArtist(artistId);
    }

    public String getAlbum_name() {
        return title;
    }


}
