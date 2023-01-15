import React, { useState, useEffect } from 'react';
import axios from 'axios';


const LoginSite = () => {
    const [login, setLogin] = useState({
        "idUser": 152
    });

    const handleChange = (e) => {
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.get('http://localhost:8080/user', {
            headers: {
                'Content-Type': 'application/json'
            },
            timeout: 0,
            data: "{test:\"test\"}"

        }).then(res => {
            console.log(res.data);
        }).catch(err => {
            console.log(err);
        });
    }

    return (
        <div>
                <label>Username</label>
                <input type="text" name="username" onChange={handleChange} />
                <label>Password</label>
                <input type="password" name="password" onChange={handleChange} />
                <button type="submit" onClick={handleSubmit}>Login</button>
        </div>
    );
}

export default LoginSite;