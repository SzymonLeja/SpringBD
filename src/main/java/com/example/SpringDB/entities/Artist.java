package com.example.SpringDB.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_artist;
    private String name;

    public void updateArtist(Artist artist) {
        this.name = artist.getName() == null ? this.name : artist.getName();
    }
}
