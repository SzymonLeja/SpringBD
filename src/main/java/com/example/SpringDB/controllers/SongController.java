package com.example.SpringDB.controllers;


import com.example.SpringDB.entities.Song;
import com.example.SpringDB.services.Songs.SongsService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/songs")
@CrossOrigin
public class SongController {

    @Autowired
    private SongsService songsService;

    @PostMapping("")
    public String addSong(@RequestBody Song song) {
        return songsService.saveSong(song);
    }

//    @GetMapping("")
//    public Song getSong(@RequestBody String songName) {
//        return songsService.getSong(songName);
//    }

    @GetMapping(value="/{songName}")
    public ResponseEntity<Resource> getSongDownload(@PathVariable("songName") String songId) throws IOException {
        String songURL = songsService.getSong(Integer.parseInt(songId)).getSongURL();
//        String tempString = "C:\\Users\\Mateu\\Documents\\Java\\SpringBD\\src\\main\\resources\\music\\";
        File file = new File(songURL);
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=metallica.mp3");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);

    }

    @PostMapping(value="/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadSong(@RequestParam MultipartFile file) throws IOException {
        String tempString = "C:\\Users\\Szymon\\Desktop\\BD2_PROJ\\src\\main\\resources\\music\\";

        File convertFile = new File(tempString+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        return "File is upload successfully";
    }

    @PutMapping("")
    public String updateSong(@RequestBody Song song) {
        return songsService.updateSong(song);
    }

    @DeleteMapping("")
    public String deletePlaylistSong(@RequestBody Integer playlistId) {
        return songsService.deleteSong(playlistId);
    }

    @GetMapping("/all")
    public List<Song> getAllSongs() {
        return songsService.getSongsRepository();
    }
}
