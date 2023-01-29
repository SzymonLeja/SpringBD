//site with info about single song and button add to playlist, user can add song to chosen playlist

import React, { useState, useEffect } from 'react';
import { Link, useParams, Navigate } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';
import Button from '@mui/material/Button';
const Song = () => {
    const [playlists, setPlaylists] = useState([]);
    const { id } = useParams();
    const [song, setSong] = useState({});
    const { auth } = useAuth();
    const [playlistId, setPlaylistId] = useState(0);
    const [audioCtx, setAudioCtx] = useState(null);
    const [source, setSource] = useState(null);
    
    useEffect(() => {
        axios.get(`http://localhost:8080/songs/g/${id}`)
            .then(res => {
                console.log(res.data)
                setSong(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/playlists/${auth.id}`)
            .then(res => {
                setPlaylists(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    useEffect(() => {
        if(!audioCtx){
            setAudioCtx(new AudioContext());
        }
        if(!source && audioCtx != null){
            setSource(audioCtx.createBufferSource());
        }
    }, [audioCtx, source])

    useEffect(() => {
        if (!audioCtx || !source) {
            console.log("xd1")
          return;
        }
        console.log("xd")

        axios.get(`http://localhost:8080/songs/i/${id}`, { responseType: 'arraybuffer' })
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
          });
      }, [audioCtx, source, id]);

    const handleSubmit = (e) => {
        e.preventDefault();
        if(playlistId != 0){
            const playlistSong = {
                idPlaylist: playlistId,
                idSong: song.idSong
            }
            axios.post(`http://localhost:8080/playlists/song`, playlistSong).then(res => {
                console.log(res);
            }).catch(err => {
                console.log(err);
            })
            window.location.href = `/playlists/${playlistId}`;
        }
    }


    const playSound = () => {
        audioCtx.resume();
    }

    const pauseSound = () => {
        audioCtx.suspend();
    }


    return (
        <div>
            <h1>Title: {song.songName}</h1>

            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={playSound}>Play</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={pauseSound}>Pause</Button>
            <div className="container">
                <form onSubmit={handleSubmit}>
                    <select onChange={(e) => setPlaylistId(e.target.value)}>
                        <option value="0">Choose playlist</option>
                        {playlists.map(playlist => <option key={playlist.idPlaylist} value={playlist.idPlaylist}>{playlist.name}</option>)}
                    </select>
                    <br />
                    <br />
                    <Button variant="contained" type="submit">Add to playlist</Button>
                </form>
            </div>
        </div>
    );
}

export default Song;
