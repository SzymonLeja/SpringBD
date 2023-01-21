// List of songs with filters, fetched from CRUD REST API from spring application using @mui with searchbar and pagination with display modal on click in row with edit and delete buttons

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataGrid } from '@mui/x-data-grid';
import { TextField } from '@mui/material';
import { makeStyles } from '@mui/styles';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import DeleteIcon from '@mui/icons-material/Delete';
import fileDownload from 'js-file-download';


function generateRandom() {
    var length = 8,
        charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789",
        retVal = "";
    for (var i = 0, n = charset.length; i < length; ++i) {
        retVal += charset.charAt(Math.floor(Math.random() * n));
    }
    return retVal;
}

const useStyles = makeStyles({
    root: {
        '& .MuiDataGrid-columnsContainer': {
            backgroundColor: '#f5f5f5'
        },
        '& .MuiDataGrid-cell': {
            backgroundColor: '#f5f5f5'
        }
    }
});

const SongsList = () => {
    const [file, setFile] = useState('');

    const classes = useStyles();
    const [songs, setSongs] = useState([]);
    const [search, setSearch] = useState('');
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [openDialog, setOpenDialog] = useState(false);
    const [openAddDialog, setOpenAddDialog] = useState(false);

    const [activeSong, setActiveSong] = useState({
        id_song: 0,
        songName: '',
        duration: '',
        release_date: '',
        id_artist: 0,
        id_album: 0
    });

    const [addSong, setAddSong] = useState({
        songName: '',
        songURL: '',
        time: '',
        size: '',
        id_genre: 0,
        id_album: 0
    });

    useEffect(() => {
        axios.get('http://localhost:8080/songs/all')
            .then(res => {
                setSongs(res.data);
                console.log(res.data);
            })
            .catch(err => {
                console.log(err);
            })
    }, []);

    const handleSearch = (e) => {
        setSearch(e.target.value);
        setPage(0);
    }

    const handlePageChange = (params) => {
        setPage(params.page);
    }

    const handleRowsPerPageChange = (params) => {
        setRowsPerPage(params.pageSize);
    }

    const handleEdit = (e) => {
        e.preventDefault();
        axios.put('http://localhost:8080/songs', activeSong)
            .then(res => {
                console.log(res.data);
                if(file != null){
                    uploadSound();
                }
                setOpenDialog(false);
            })
            .catch(err => {
                console.log(err);
            })
    }

    const handleDelete = (e) => {
        e.preventDefault();
        axios.delete('http://localhost:8080/songs/delete/' + activeSong.id_song)
            .then(res => {
                console.log(res.data);
                setOpenDialog(false);
            })
            .catch(err => {
                console.log(err);
            })
    }

    const handleAdd = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/songs', addSong)
            .then(res => {
                axios.get('http://localhost:8080/songs/all')
                .then(resAll => {
                    setSongs(resAll.data);
                    if(file != null){
                        uploadSound()
                    }
                    console.log("updated list")
                })
                console.log(res.data);
                setOpenAddDialog(false);
            })
            .catch(err => {
                console.log(err);
            })
    }

    const columns = [
        { field: 'id_song', headerName: 'ID', width: 70 },
        { field: 'songURL', headerName: 'URL', width: 150 },
        { field: 'songName', headerName: 'Title', width: 150 },
        { field: 'time', headerName: 'Duration', width: 150 },
        { field: 'size', headerName: 'Size', width: 150 },
        { field: 'album.title', headerName: 'Album', width: 150, valueGetter: (params) => params.row.album.title},
        { field: 'genre.name', headerName: 'Genre', width: 150, valueGetter: (params) => params.row.genre.name },
        { field: 'album.artist.name', headerName: 'Artist', width: 150, valueGetter: (params) => params.row.album.artist.name },
    ];

    const rows = songs.filter((song) => {
        return song.songName.toLowerCase().includes(search.toLowerCase());
    });


    const uploadSound = () => {
        console.log(file)
        const formData = new FormData();
        formData.append('file', file);
        axios.post('http://localhost:8080/songs/upload', formData)
        .then(res => {
            console.log(res);
            console.log(res.data);
        })

    }
    return (
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid
            style={{ width: "80%", margin: "auto", height: "80vh"}} 
                rows={rows}
                columns={columns}
                pageSize={rowsPerPage}
                rowsPerPageOptions={[5, 10, 20]}
                checkboxSelection
                disableSelectionOnClick
                onRowClick={(e) => {
                    setActiveSong(e.row);
                    setOpenDialog(true);
                }}
                onPageChange={handlePageChange}
                onPageSizeChange={handleRowsPerPageChange}
                getRowId={(row) => row.id_song}

                components={{
                    Toolbar: () => (
                        <div style={{ display: 'flex', alignItems: 'center', marginBottom: 20 }}>
                        <TextField
                            label="Search"
                            variant="outlined"
                            size="small"
                            value={search}
                            onChange={handleSearch}
                        />
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={() => setOpenAddDialog(true)}
                        >
                            Add
                        </Button>
                    </div>
                    )
                }}


            />
            <Dialog open={openDialog} onClose={() => setOpenDialog(false)}>
                <DialogTitle>Edit Song</DialogTitle>
                <DialogContent>
                        <TextField
                            label="Title"
                            variant="outlined"
                            size="small"
                            value={activeSong.songName}
                            onChange={(e) => setActiveSong({ ...activeSong, songName: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="URL"
                            variant="outlined"
                            size="small"
                            value={activeSong.songURL}
                            onChange={(e) => setActiveSong({ ...activeSong, songURL: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="Duration"
                            variant="outlined"
                            size="small"
                            value={activeSong.time}
                            onChange={(e) => setActiveSong({ ...activeSong, time: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="Size"
                            variant="outlined"
                            size="small"
                            value={activeSong.size}
                            onChange={(e) => setActiveSong({ ...activeSong, size: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="Album"
                            variant="outlined"
                            size="small"
                            value={activeSong.id_album}
                            onChange={(e) => setActiveSong({ ...activeSong, id_album: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="Genre"
                            variant="outlined"
                            size="small"
                            value={activeSong.id_genre}
                            onChange={(e) => setActiveSong({ ...activeSong, id_genre: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                    <input type="file" onChange={e => setFile(e.target.files[0])} />
                </DialogContent>
                <DialogActions>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleEdit}
                    >
                        Edit
                    </Button>
                    <Button
                        variant="contained"
                        color="secondary"
                        onClick={handleDelete}
                    >
                        Delete
                    </Button>
                    <Button 
                        variant="contained"
                        onClick={() => setOpenDialog(false)}
                    >
                        Cancel
                    </Button>
                </DialogActions>
            </Dialog>
            <Dialog open={openAddDialog} onClose={() => setOpenAddDialog(false)}>
                <DialogTitle>Add Song</DialogTitle>
                <DialogContent>
                        <TextField
                            label="Title"
                            variant="outlined"
                            size="small"
                            value={addSong.song_name}
                            onChange={(e) => setAddSong({ ...addSong, songName: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="URL"
                            variant="outlined"
                            size="small"
                            value={addSong.songURL}
                            onChange={(e) => setAddSong({ ...addSong, songURL: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField

                            label="Duration"
                            variant="outlined"
                            size="small"
                            value={addSong.time}
                            onChange={(e) => setAddSong({ ...addSong, time: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField

                            label="Size"
                            variant="outlined"
                            size="small"
                            value={addSong.size}
                            onChange={(e) => setAddSong({ ...addSong, size: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField

                            label="Album"
                            variant="outlined"
                            size="small"
                            value={addSong.id_album}
                            onChange={(e) => setAddSong({ ...addSong, id_album: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <TextField
                            label="Genre"
                            variant="outlined"
                            size="small"
                            value={addSong.id_genre}
                            onChange={(e) => setAddSong({ ...addSong, id_genre: e.target.value })}
                            style={{ marginBottom: 20 }}
                        />
                        <input type="file" onChange={e => setFile(e.target.files[0])} />
                </DialogContent>
                <DialogActions>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleAdd}
                    >
                        Add
                    </Button>
                    <Button

                        variant="contained"
                        onClick={() => setOpenAddDialog(false)}
                    >
                        Cancel
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
                
    }

    export default SongsList;