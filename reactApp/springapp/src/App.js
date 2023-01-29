//Main APP, Spring Boot App is running on port 8080, using ReactRouter to navigate

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import ArtistsList from './components/ArtistsList';
import SongsList from './components/SongsList';
import AlbumsList from './components/AlbumsList';
import LoginSite from './components/LoginSite';
import UserPlaylists from "./components/UserPlaylists";
import RequireAuth from './components/RequireAuth';
import PlayerSite from "./Player/PlayerSite";
import Home from "./components/Home";
import Navmenu from "./nav/Navmenu";
import Playlist from './components/Playlist';
import Song from './components/Song';
import Album from './components/Album';
import Artists from './components/Artists';

function App() {
  // is user logged in?
  
  return (
    <div>
          <Navmenu></Navmenu>

        <Routes>
          <Route exact path={"/"} element={<PlayerSite/>} />
          <Route exact path={"/login"} element={<LoginSite/>} />

          <Route element={<RequireAuth allowedType={["moderator"]}/>} >
            <Route exact path={"/artists"} element={<ArtistsList/>}  />
            <Route exact path={"/songs"} element={<SongsList/>} />
            <Route exact path={"/albums"} element={<AlbumsList/>} />
          </Route>
          <Route element={<RequireAuth allowedType={["moderator", "user"]}/>} >
            <Route exact path={"/playlists"} element={<UserPlaylists/>} />
            <Route exact path={"/playlist/:id"} element={<Playlist/>} />
            <Route exact path={"/song/:id"} element={<Song/>} />
            <Route exact path={"/album/:id"} element={<Album/>} />
            <Route exact path={"/artist/:id"} element={<Artists />} />

          </Route>
        </Routes>
    </div>

  );
}

export default App;


