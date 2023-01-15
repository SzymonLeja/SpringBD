package com.example.SpringDB.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_playlist;
    private String name;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "idUser")
    @Name("idUser")
    private User user;

    @JsonProperty("idUser") private void unpackNested(Integer userId) {
        this.user = new User();
        user.setIdUser(userId);
    }


}
