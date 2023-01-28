//fetch list of playlists of active user

import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';

const UserPlaylists = () => {
    const [playlists, setPlaylists] = useState([]);
    const { auth } = useAuth();


    useEffect(() => {
        axios.get(`http://localhost:8080/playlists/${auth.id}`)
            .then(res => {
                console.log(res.data)
                setPlaylists(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    return (
        <div>
            <h1>Playlists</h1>
            <div className="container">
                <Link to="/createplaylist">Create Playlist</Link>
            </div>
            <div className="container">
                {playlists.map(playlist => <div key={playlist.idPlaylist}><Link to={`/playlist/${playlist.idPlaylist}`}>{playlist.name}</Link></div>)}
            </div>
        </div>
    );
}

export default UserPlaylists;