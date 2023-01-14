//Main APP, Spring Boot App is running on port 8080, using ReactRouter to navigate

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import ArtistsList from './components/ArtistsList';
import SongsList from './components/SongsList';
import AlbumsList from './components/AlbumsList';
import LoginSite from './components/LoginSite';
import UserPlaylists from "./components/UserPlaylists";
import PlayerSite from "./Player/PlayerSite";

function App() {

  return (
    <Router>
      <div>
        <Routes>
          <Route exact path={"/artists"} element={<ArtistsList/>} />
          <Route exact path={"/songs"} element={<SongsList/>} />
          <Route exact path={"/albums"} element={<AlbumsList/>} />
          <Route exact path={"/login"} element={<LoginSite/>} />
          <Route exact path={"/playlists"} element={<UserPlaylists/>} />
          <Route exact path={"/playersite"} element={<PlayerSite/>} />


        </Routes>
      </div>
    </Router>
  );
}

export default App;


