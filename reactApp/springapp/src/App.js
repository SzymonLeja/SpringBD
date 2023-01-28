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

function App() {
  // is user logged in?
  
  return (
    <div>
          <Navmenu></Navmenu>

        <Routes>
          <Route exact path={"/"} element={<Home/>} />
          <Route exact path={"/login"} element={<LoginSite/>} />

          <Route element={<RequireAuth allowedType={["moderator"]}/>} >
            <Route exact path={"/artists"} element={<ArtistsList/>}  />
            <Route exact path={"/songs"} element={<SongsList/>} />
            <Route exact path={"/albums"} element={<AlbumsList/>} />
          </Route>
          <Route element={<RequireAuth allowedType={["moderator", "user"]}/>} >
            <Route exact path={"/playersite"} element={<PlayerSite/>} />
            <Route exact path={"/playlists"} element={<UserPlaylists/>} />
            <Route exact path={"/playlist/:id"} element={<Playlist/>} />

          </Route>
        </Routes>
    </div>

  );
}

export default App;


