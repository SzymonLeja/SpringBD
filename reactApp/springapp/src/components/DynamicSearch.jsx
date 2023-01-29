// search bar that searches for songs, albums and artists
// simple search box without playing songs

import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import useAuth from '../hooks/useAuth';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

const DynamicSearch = () => {
    const [search, setSearch] = useState('');
    const [searchResults, setSearchResults] = useState({});
    const { auth } = useAuth();

    const handleSearch = () => {
        axios.get(`http://localhost:8080/search/${search}`)
        .then(res => {
            setSearchResults(res.data[0]);
        }).catch(err => {
            console.log(err);
        }
        )
    }

    return (
        <div>
            <TextField
                id="outlined-basic"
                label="Search"
                variant="outlined"
                onChange={e => setSearch(e.target.value)}
            />
            <Button variant="contained" onClick={handleSearch}>Search</Button>
            <div className="container">
            {Array.isArray(searchResults) && searchResults.map(result => {
                if(Array.isArray(result) && (result.length > 0)){
                    return result.map(object => {
                        if(object.idSong){
                            console.log("Song: " + object.songName);
                            return <div key={object.idSong}><Link to={`/song/${object.idSong}`}>{object.songName} - Song</Link></div>

                        } else if(object.idAlbum){
                            console.log("Album: " + object.idAlbum);
                            return <div key={object.idAlbum}><Link to={`/album/${object.idAlbum}`}>{object.title} - Album</Link></div>
                        } else if(object.idArtist){
                            console.log("Artist: " + object.idArtist);
                            return <div key={object.idArtist}><Link to={`/artist/${object.idArtist}`}>{object.artistName} - Artist</Link></div>
                        }
                    })
                }


            })}



            </div>
        </div>
    );
}

export default DynamicSearch;