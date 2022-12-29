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
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_genre;
    private String name;

    public String getGenre_name() {
        return name;
    }

    public void updateGenre(Genre genre) {
        this.name = genre.getName() == null ? this.name : genre.getName();
    }
}
