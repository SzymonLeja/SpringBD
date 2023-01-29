// view with info about albums and songs of artist

import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';
import Album from './Album';
import { Button } from '@mui/material';
import { Box } from '@mui/system';
import { Grid } from '@mui/material';
import { Typography } from '@mui/material';
import { Card } from '@mui/material';
import { CardContent } from '@mui/material';
import { CardActionArea } from '@mui/material';
import { CardActions } from '@mui/material';
import { IconButton } from '@mui/material';
import { PlayArrow } from '@mui/icons-material';
import { Pause } from '@mui/icons-material';
import { FavoriteBorder } from '@mui/icons-material';
import { PlaylistAdd } from '@mui/icons-material';
import { PlaylistAddCheck } from '@mui/icons-material';

const Artists = () => {
    const [artist, setArtist] = useState({});
    const [albums, setAlbums] = useState([]);
    const [songs, setSongs] = useState([]);
    const { id } = useParams();
    const { auth } = useAuth();
    const [playlistId, setPlaylistId] = useState(0);

    useEffect(() => {
        axios.get(`http://localhost:8080/artists/g/${id}`)
            .then(res => {
                console.log(res.data)
                setArtist(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/albums/artist/${id}`)
            .then(res => {
                console.log(res.data)
                setAlbums(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/songs/artist/${id}`)
            .then(res => {
                console.log(res.data)
                setSongs(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);



    const handleAddToPlaylist = (songId) => {
        let playlistSong = {
            idPlaylist: id,
            idSong: songId
        }
        axios.post(`http://localhost:8080/playlists/song`, playlistSong)
            .then(res => {
                console.log(res.data)
            }).catch(err => {
                console.log(err);
            }
            )
    }

    return (
        <div>
        <h1>Title: {artist.name}</h1>

        <h2>Albums</h2>
        <Grid container spacing={2}>
            {albums.map(album => (
                <Grid item xs={12} sm={6} md={4} lg={3} key={album.id}>
                    <Card sx={{ maxWidth: 345 }}>
                        <CardActionArea>
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                    {album.title}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    {album.year}
                                </Typography>
                            </CardContent>
                        </CardActionArea>
                        <CardActions>
                            <Link to={`/album/${album.idAlbum}`}>
                                <Button size="small" color="primary">
                                    View
                                </Button>
                            </Link>
                        </CardActions>
                    </Card>
                </Grid>
            ))}

        </Grid>

        <h2>Songs</h2>
        <Grid container spacing={2}>
            {songs.map(song => (

                <Grid item xs={12} sm={6} md={4} lg={3} key={song.id}>
                    <Card sx={{ maxWidth: 345 }}>
                        <CardActionArea>
                            <CardContent>
                                <Typography gutterBottom variant="h5" component="div">
                                    {song.songName}
                                </Typography>
                                <Typography variant="body2" color="text.secondary">
                                    {song.album.title}
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
    )
    
}

export default Artists;
