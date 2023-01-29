// view with info about album and list of songs in album

import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';
import Song from './Song';
import { Button } from '@mui/material';
import { Box } from '@mui/system';
import { Grid } from '@mui/material';
import { Typography } from '@mui/material';
import { Card } from '@mui/material';
import { CardContent } from '@mui/material';
import { CardMedia } from '@mui/material';
import { CardActionArea } from '@mui/material';
import { CardActions } from '@mui/material';
import { IconButton } from '@mui/material';
import { PlayArrow } from '@mui/icons-material';
import { Pause } from '@mui/icons-material';
import { FavoriteBorder } from '@mui/icons-material';
import { PlaylistAdd } from '@mui/icons-material';
import { PlaylistAddCheck } from '@mui/icons-material';


const Album = () => {
    const [album, setAlbum] = useState({});
    const [songs, setSongs] = useState([]);
    const [playlists, setPlaylists] = useState([]);
    const { id } = useParams();
    const { auth } = useAuth();
    const [playlistId, setPlaylistId] = useState(0);

    useEffect(() => {
        axios.get(`http://localhost:8080/albums/g/${id}`)
            .then(res => {
                console.log(res.data)
                setAlbum(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/songs/album/${id}`)
            .then(res => {
                console.log(res.data)
                setSongs(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

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

    const handleSubmit = (e) => {
        e.preventDefault();
        if(playlistId != 0){
            const playlistSong = {
                idPlaylist: playlistId,
            }
            songs.map(song => { 
                playlistSong.idSong = song.idSong;
                axios.post(`http://localhost:8080/playlists/song`, playlistSong)
                .then(res => {
                    console.log(res.data)
                }
                ).catch(err => {
                    console.log(err);
                }
            )})
        }
    }

    return (
        <div>
            <h1>Title: {album.title}</h1>
            <h3>Number of songs: {songs.length}</h3>
            <div className="container">
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="playlist">Choose playlist:</label>
                        <select className="form-control" id="playlist" onChange={e => setPlaylistId(e.target
                            .value)}>
                            <option value="0">Choose playlist</option>
                            {playlists.map(playlist => (
                                <option value={playlist.idPlaylist}>{playlist.name}</option>
                            ))}
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary">Add to playlist</button>
                </form>
            </div>
            <div>
                <h1>Songs</h1>
                <Grid container spacing={2}>
                    {songs.map(song => (
                        <Grid item xs={12} sm={6} md={4} lg={3}>
                            <Card sx={{ maxWidth: 345 }}>
                                <CardActionArea>
                                    <CardContent>
                                        <Typography gutterBottom variant="h5" component="div">
                                            {song.songName}
                                        </Typography>

                                    </CardContent>
                                </CardActionArea>
                                <CardActions>
                                <Link to={`/song/${song.idSong}`}>
                                <Button size="small" color="primary">
                                    View
                                </Button>
                            </Link>
                                </CardActions>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            </div>
        </div>
    )
}

export default Album;