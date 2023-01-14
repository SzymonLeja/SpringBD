// List of artists with filters, fetched from CRUD REST API from spring application using @mui with searchbar and pagination with display modal on click in row with edit and delete buttons

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

const UserPlaylists = () => {
    const classes = useStyles();
    const [artists, setArtists] = useState([]);
    const [search, setSearch] = useState('');
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [openDialog, setOpenDialog] = useState(false);
    const [openAddDialog, setOpenAddDialog] = useState(false);

    const [activeArtist, setActiveArtist] = useState({
        id_artist: 0,
        name: ''
    });

    const [addArtist, setAddArtist] = useState({
        name: ''
    });


    useEffect(() => {
        axios.get('http://localhost:8080/artists/all')
            .then(res => {
                setArtists(res.data);
                console.log(res.data);
            })
    }, []);

    const columns = [
        { field: 'id_artist', headerName: 'ID', width: 70 },
        { field: 'name', headerName: 'Name', width: 130 },
    ];

    const handleFocusArtist = (artist) => {
        setActiveArtist({
            id_artist: artist.id_artist,
            name: artist.name
        });
    }

    const handleChangeName = () => {
        const tempArtist = {
            id_artist: activeArtist.id_artist,
            name: activeArtist.name
        }
        axios.put('http://localhost:8080/artists', tempArtist)
            .then(res => {
                artists.map(artist => {
                    if (artist.id_artist === activeArtist.id_artist) {
                        artist.name = activeArtist.name;
                    }
                });
                console.log(res.data);
                handleClose();
            });
    }
    const handleDelete = () => {
        console.log(activeArtist.id_artist)
        axios.delete('http://localhost:8080/artists', { data: activeArtist.id_artist })
            .then(res => {
                artists.map((artist, index) => {
                    if (artist.id_artist === activeArtist.id_artist) {
                        artists.splice(index, 1);
                    }
                });
                console.log(res.data);
                handleClose();
            }).catch(err => {
            console.log(err);
        });
    }

    useEffect(() => {
        activeArtist.name !== '' ? setOpenDialog(true) : setOpenDialog(false);
        console.log(activeArtist)
    }, [activeArtist]);

    const handleSearch = (event) => {
        setSearch(event.target.value);
    }

    const handleCloseAddDialog = () => {
        setOpenAddDialog(false);
    };
    const handleOpenAddDialog = () => {
        setOpenAddDialog(true);
    };

    const handleAddArtist = () => {
        const tempArtist = {
            name: addArtist.name
        }
        axios.post('http://localhost:8080/artists', tempArtist)
            .then(res => {
                axios.get('http://localhost:8080/artists/all')
                    .then(res => {
                        setArtists(res.data);
                    })
                console.log(res.data);
                handleCloseAddDialog();
            });
    }
    const handleClose = () => {
        setOpenDialog(false);
    };

    const handleOpenDialog = (artist) => {
        handleFocusArtist(artist)
    };
    const handlePageChange = (params) => {
        setPage(params.page);
    }

    const handleRowsPerPageChange = (params) => {
        setRowsPerPage(params.pageSize);
    }

    const filteredArtists = artists.filter((artist) => {
        return artist.name.toLowerCase().includes(search.toLowerCase());
    })

    return (
        <div style={{ height: 400, width: '100%' }}>
            <div style={{ display: 'flex', height: '100%', width: '100%' }}>
                <div style={{ flexGrow: 1 }}>
                    <DataGrid
                        style={{ width: "80%", margin: "auto", height: "80vh"}}
                        rows={filteredArtists}
                        columns={columns}
                        pageSize={rowsPerPage}
                        rowsPerPageOptions={[5, 10, 20]}
                        checkboxSelection
                        disableSelectionOnClick
                        getRowId={(row) => row.id_artist}
                        //onRowClick display modal with edit and delete buttons
                        onRowClick={(row) => {
                            handleOpenDialog(row.row);
                        }}
                        onPageChange={handlePageChange}
                        onRowsPerPageChange={handleRowsPerPageChange}
                        page={page}

                        components={{
                            Toolbar: () => (
                                <div>
                                    <TextField
                                        style={{ margin: 8 }}
                                        label="Search"
                                        variant="outlined"
                                        size="small"
                                        value={search}
                                        onChange={handleSearch}
                                    />
                                    <Button variant="contained" style={{marginTop: "10px"}} onClick={handleOpenAddDialog}>Add</Button>
                                </div>

                            )
                        }}
                    />
                </div>
            </div>
            <Dialog
                open = {openDialog}
            >
                <DialogTitle>Artist</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="nameEdit"
                        label="Name"
                        type="text"
                        fullWidth
                        value={activeArtist.name}
                        onChange={(e) => setActiveArtist({ ...activeArtist, name: e.target.value })}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose}>Cancel</Button>
                    <Button onClick={handleChangeName}>Save</Button>
                    <Button onClick={handleDelete} startIcon={<DeleteIcon/>}>Delete</Button>

                </DialogActions>
            </Dialog>

            <Dialog
                open={openAddDialog}
                onClose={handleCloseAddDialog}
                aria-labelledby="form-dialog-title"
            >
                <DialogTitle id="form-dialog-title">Add Artist</DialogTitle>
                <DialogContent>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="nameAdd"
                        label="Name"
                        type="text"
                        fullWidth
                        onChange={(e) => setAddArtist({ ...addArtist, name: e.target.value })}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseAddDialog} color="primary">
                        Cancel
                    </Button>
                    <Button onClick={handleAddArtist} color="primary">
                        Add
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}

export default UserPlaylists;
