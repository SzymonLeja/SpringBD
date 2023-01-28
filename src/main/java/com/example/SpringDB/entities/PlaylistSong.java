package com.example.SpringDB.entities;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "playlists_songs")
public class PlaylistSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlaylistSong;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "idPlaylist")
    private Playlist playlist;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "idSong")
    private Song song;

    @JsonProperty("idPlaylist") private void unpackNested(Integer playlistId) {
        this.playlist = new Playlist();
        playlist.setIdPlaylist(playlistId);
    }
    @JsonProperty("idSong") private void unpackNested2(Integer songId) {
        this.song = new Song();
        song.setIdSong(songId);
    }

}
