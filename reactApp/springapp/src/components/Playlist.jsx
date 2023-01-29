import React, { useState, useEffect} from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import DynamicSearch from './DynamicSearch';

const Playlist = () => {
    const [search, setSearch] = useState('');
    const [file, setFile] = useState('');
    const [ activeSong, setActiveSong ] = useState(0);
    const { id } = useParams();
    const [songs, setSongs] = useState([]);
    const { auth } = useAuth();
    const audioCtx = new (window.AudioContext || window.webkitAudioContext)();
    let source = audioCtx.createBufferSource();



    useEffect(() => {
        axios.get(`http://localhost:8080/playlists/song/${id}`)
            .then(res => {
                let songsArr = [];
                for (let i = 0; i < res.data.length; i++) {
                    songsArr.push(res.data[i].song);
                }
                setSongs(songsArr);
            }).catch(err => {
                console.log(err);
            }
            )

    }, []);

    useEffect(() => {
        if(songs.length != 0){
            let getSongId = songs[activeSong].idSong;
            console.log(getSongId);
            axios.get(`http://localhost:8080/songs/${getSongId}`, { responseType: 'arraybuffer' })
            .then(res => {
                audioCtx.decodeAudioData(res.data, (buffer) => {
                    source.buffer = buffer;
                    source.connect(audioCtx.destination);
                });
                source.start();
                audioCtx.suspend();
            })
            .catch(err => {
                console.log(err);
            }
            )
        }

    }, [songs, activeSong])


    const playSound = () => {
        audioCtx.resume();
    }

    const pauseSound = () => {
        audioCtx.suspend();
    }

    const nextSound = () => {
        if(activeSong < songs.length - 1){
            pauseSound();
            setActiveSong(activeSong + 1);
        }
    }

    const prevSound = () => {
        if(activeSong > 0){
            pauseSound();
            setActiveSong(activeSong - 1);
        }
    }

    return (
        <div>
            <h1>Playlist</h1>
            <div className="container">
            <h3 className="mb-4">Currently playing: {songs[activeSong]?songs[activeSong].album.artist.name + " - " + songs[activeSong].songName: null}</h3>
            <DynamicSearch/>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={prevSound}>Prev</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={playSound}>Play</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={pauseSound}>Pause</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={nextSound}>Next</Button>
            {songs.map(song => <div key={song.idSong}><Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={() => {pauseSound(); setActiveSong(songs.indexOf(song))}}>{song.songName}</Button></div>)}

            </div>
        </div>
    );
}

export default Playlist;