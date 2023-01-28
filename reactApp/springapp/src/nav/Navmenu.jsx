//Nav menu component using @mui with links to other components

import React from 'react';
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import { AppBar, Toolbar, Typography, Button, IconButton, Menu, MenuItem } from '@mui/material';
import MenuIcon from '@mui/icons-material/Menu';

const Navmenu = () => {
    const { auth, logout } = useAuth();
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);


    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        logout();
    };

    const handleLogin = () => {
        //redirect to /login
        document.location.href = "/login";
    };

    return (
        <div>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        aria-controls="menu-appbar"
                        aria-haspopup="true"
                        onClick={handleClick}
                        sx={{ mr: 2 }}
                    >
                        <MenuIcon />
                    </IconButton>
                    <Menu
                        id="menu-appbar"
                        anchorEl={anchorEl}
                        anchorOrigin={{
                            vertical: 'top',
                            horizontal: 'right',
                        }}
                        keepMounted
                        transformOrigin={{
                            vertical: 'top',
                            horizontal: 'right',
                        }}
                        open={open}
                        onClose={handleClose}
                    >
                        <MenuItem onClick={handleClose}><Link to="/">Home</Link></MenuItem>
                        {auth.type === "moderator" ? 
                            ([
                            <MenuItem onClick={handleClose}><Link to="/albums">Albums</Link></MenuItem>,
                            <MenuItem onClick={handleClose}><Link to="/songs">Songs</Link></MenuItem>,
                            <MenuItem onClick={handleClose}><Link to="/artists">Artists</Link></MenuItem>,
                            ])
                        : null}
                        <MenuItem onClick={handleClose}><Link to="/playersite">Player</Link></MenuItem>
                        <MenuItem onClick={handleClose}><Link to="/playlists">Playlists</Link></MenuItem>,

                    </Menu>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    <MenuItem onClick={handleClose}><Link to="/">Home</Link></MenuItem>
                    
                    </Typography>
                    <Button color="inherit" onClick={auth.id != null ? handleLogout : handleLogin }>{auth.id != null ? "Logout" : "Login" }</Button>
                </Toolbar>
            </AppBar>
        </div>
    );
}

export default Navmenu;
