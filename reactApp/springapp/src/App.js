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

function App() {
  // is user logged in?
  const [loggedIn, setLoggedIn] = React.useState(false);
  const [user, setUser] = React.useState(null);
  

  return (
      <div>
        <Routes>
          <Route exact path={"/login"} element={<LoginSite/>} />

          <Route element={<RequireAuth allowedType={["moderator"]}/>} >
            <Route exact path={"/artists"} element={<ArtistsList/>}  />
            <Route exact path={"/songs"} element={<SongsList/>} />
            <Route exact path={"/albums"} element={<AlbumsList/>} />
            <Route exact path={"/playlists"} element={<UserPlaylists/>} />
          </Route>
          <Route element={<RequireAuth allowedType={["moderator", "user"]}/>} >
            <Route exact path={"/playersite"} element={<PlayerSite/>} />
          </Route>
        </Routes>
      </div>
  );
}

export default App;


