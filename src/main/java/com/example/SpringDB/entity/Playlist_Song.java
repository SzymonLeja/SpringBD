package com.example.SpringDB.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlists_songs")
public class Playlist_Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_playlist_song;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_playlist")
    private Playlist playlist;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "id_song")
    private Song song;

    @JsonProperty("id_playlist") private void unpackNested(Integer playlistId) {
        this.playlist = new Playlist();
        playlist.setId_playlist(playlistId);
    }
    @JsonProperty("id_song") private void unpackNested2(Integer songId) {
        this.song = new Song();
        song.setId_song(songId);
    }

}
