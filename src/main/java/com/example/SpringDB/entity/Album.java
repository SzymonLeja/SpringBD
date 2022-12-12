package com.example.SpringDB.entity;

import com.example.SpringDB.repository.ArtistsRepository;
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
    @Column(name = "id_album")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_album;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_artist")
    private Artist artist;
    @Column(name = "title")
    private String title;


    @JsonProperty("id_artist") private void unpackNested(Integer artistId) {
        this.artist = new Artist();
        artist.setId_artist(artistId);
    }
}
