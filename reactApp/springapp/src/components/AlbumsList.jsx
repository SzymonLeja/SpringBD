// Same as SongsList.jsx but for albums

import React from 'react';
import { DataGrid } from '@mui/x-data-grid';
import { makeStyles } from '@mui/styles';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import DeleteIcon from '@mui/icons-material/Delete';
import TextField from '@mui/material/TextField';
import { useState, useEffect } from 'react';
import axios from 'axios';

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

const AlbumsList = () => {
    const classes = useStyles();
    const [albums, setAlbums] = useState([]);
    const [search, setSearch] = useState('');
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [openDialog, setOpenDialog] = useState(false);
    const [openAddDialog, setOpenAddDialog] = useState(false);

    const [activeAlbum, setActiveAlbum] = useState({
        idAlbum: 0,
        title: ''
    });

    const [addAlbum, setAddAlbum] = useState({
        title: '',
        idArtist: 0
    });

    useEffect(() => {
        axios.get('http://localhost:8080/albums/all')
            .then(res => {
                console.log(res);
                setAlbums(res.data);
            }).catch(err => {
                console.log(err);
            }
            )
    }, []);

    const columns = [
        { field: 'idAlbum', headerName: 'ID', width: 70 },
        { field: 'title', headerName: 'Album', width: 200 },
        { field: 'artist', headerName: 'Artist', width: 200, valueGetter: (params) => `${params.row.artist.name}` },
    ];

    const handleDelete = () => {
        axios.delete('http://localhost:8080/albums' + activeAlbum.idAlbum)
            .then(res => {
                setAlbums(albums.filter(album => album.idAlbum !== activeAlbum.idAlbum));
            })
        setOpenDialog(false);
    }

    const handleAdd = () => {
        console.log(addAlbum);
        axios.post('http://localhost:8080/albums', addAlbum)
            .then(res => {
                console.log(res);
                console.log(res.data);
                setAlbums([...albums, res.data]);
            })
        setOpenAddDialog(false);
    }

    const handleEdit = () => {
        axios.put('http://localhost:8080/albums', activeAlbum)
            .then(res => {
                console.log(res);
                console.log(res.data);
                setAlbums(albums.map(album => album.idAlbum === activeAlbum.idAlbum ? activeAlbum : album));
            })
        setOpenDialog(false);
    }


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

    const handleOpenDialog = (params) => {
        setOpenDialog(true);
        setActiveAlbum(params.row);
    }

    const handleCloseDialog = () => {
        setOpenDialog(false);
    }

    const handleOpenAddDialog = () => {
        setOpenAddDialog(true);
    }

    const handleCloseAddDialog = () => {
        setOpenAddDialog(false);
    }

    const handleAddAlbum = (e) => {
        setAddAlbum({ ...addAlbum, [e.target.name]: e.target.value });
    }

    const rows = albums.filter(album => album.title.toLowerCase().includes(search.toLowerCase()))
        .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage);
        

    return (
        <div style={{ height: 400, width: '100%' }}>
            <DataGrid 
                rows={rows}
                columns={columns}
                pageSize={rowsPerPage}
                rowsPerPageOptions={[5, 10, 20]}
                checkboxSelection
                disableSelectionOnClick
                className={classes.root}
                onRowClick={handleOpenDialog}
                onPageChange={handlePageChange}
                onPageSizeChange={handleRowsPerPageChange}
                getRowId={row => row.idAlbum}
                
                components={{
                    Toolbar: () => (
                        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
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

            <Dialog
                open={openDialog}
                onClose={handleCloseDialog}
            >
                <DialogTitle>Edit album</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Album"
                        type="text"
                        fullWidth
                        value={activeAlbum.title}
                        onChange={(e) => setActiveAlbum({ ...activeAlbum, title: e.target.value })}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleEdit} color="primary">
                        Edit
                    </Button>
                    <Button onClick={handleDelete} color="primary">
                        Delete
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog
                open={openAddDialog}
                onClose={handleCloseAddDialog}
            >
                <DialogTitle>Add album</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="title"
                        label="Album"
                        type="text"
                        name="title"
                        fullWidth
                        onChange={handleAddAlbum}
                    />
                    <TextField

                        autoFocus
                        margin="dense"
                        id="id_artist"
                        label="Artist"
                        type="text"
                        name="id_artist"
                        fullWidth
                        onChange={handleAddAlbum}
                    />

                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseAddDialog} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleAdd} color="primary">
                        Add
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default AlbumsList;




