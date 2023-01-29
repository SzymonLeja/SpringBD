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
    private Integer idArtist;
    private String name;

    public String getArtistName() {
        return name;
    }

    @Override
    public String toString() {
        return  "\n{\nid: " + idArtist + ",\nartistName: " + name + "\n}";
    }
}
