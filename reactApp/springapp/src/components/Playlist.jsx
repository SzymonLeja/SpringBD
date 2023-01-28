import React, { useState, useEffect} from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';

const Playlist = () => {
    const { id } = useParams();
    const [songs, setSongs] = useState([]);
    const { auth } = useAuth();

    useEffect(() => {
        axios.get(`http://localhost:8080/playlists/song/${id}`)
            .then(res => {
                console.log(res.data[0].song)
                let songs = [];
                for (let i = 0; i < res.data.length; i++) {
                    songs.push(res.data[i].song);
                }
                setSongs(songs);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    return (
        <div>
            <h1>Playlist</h1>
            <div className="container">
                    {songs.map(song => <div key={song.idSong}>{song.songName}</div>)}
            </div>
        </div>
    );
}

export default Playlist;