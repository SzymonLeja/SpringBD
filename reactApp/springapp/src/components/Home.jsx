//home component with links to other components

import React from 'react';
import { Link } from 'react-router-dom';
import useAuth from '../hooks/useAuth';

const Home = () => {
    const { auth, logout } = useAuth();
  
    const handleLogout = () => {
      logout();
    };
  
    return (
      <div>
        <h1>Home</h1>
        <div className="container">
          <Link to="/songs">Admin</Link>
        </div>
        <div className="container">
          <Link to="/playersite">User</Link>
        </div>
        <button onClick={handleLogout}>Logout</button>
      </div>
    );
  };

export default Home;