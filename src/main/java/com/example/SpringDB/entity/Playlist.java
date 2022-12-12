package com.example.SpringDB.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @JoinColumn(name = "id_user")
    private User user;

    @JsonProperty("id_user") private void unpackNested(Integer userId) {
        this.user = new User();
        user.setId_user(userId);
    }


}
