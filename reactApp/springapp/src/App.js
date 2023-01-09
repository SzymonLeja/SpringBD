//Main APP, Spring Boot App is running on port 8080, using ReactRouter to navigate

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import ArtistsList from './components/ArtistsList';
import SongsList from './components/SongsList';
import AlbumsList from './components/AlbumsList';
import axios from 'axios';

function App() {


  return (
    <Router>
      <div>
        <Routes>
          <Route exact path={"/artists"} element={<ArtistsList/>} />
          <Route exact path={"/songs"} element={<SongsList/>} />
          <Route exact path={"/albums"} element={<AlbumsList/>} />

        </Routes>
      </div>
    </Router>
  );
}

export default App;


