//Main APP, Spring Boot App is running on port 8080, using ReactRouter to navigate

import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';
import AddArtist from './components/AddArtist';
import Login from './components/AddArtist';
import ArtistsList from './components/ArtistsList';

function App() {


  return (
    <Router>
      <div>
        <Routes>
          <Route exact path={"/artists"} element={<ArtistsList/>} />
          <Route exact path={"/artists/add"} element={<AddArtist/>} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;


