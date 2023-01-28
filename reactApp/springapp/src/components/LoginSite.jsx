import React, { useState, useEffect, useContext } from 'react';
import useAuth from '../hooks/useAuth';
import axios from 'axios';
import { Link, useNavigate, useLocation } from 'react-router-dom';
import AuthContext from '../contexts/AuthProvider';

const LoginSite = () => {
    const { setAuth } = useAuth();
    const navigate = useNavigate();
    const location = useLocation();
    const from = location.state?.from?.pathname || '/';
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [hasLoginFailed, setHasLoginFailed] = useState(false);
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);

    const handleChange = (event) => {
        if (event.target.name === 'username') {
            setUsername(event.target.value);
        } else if (event.target.name === 'password') {
            setPassword(event.target.value);
        }
    }

    const loginClicked = () => {
        axios.get(`http://localhost:8080/user/l/${username}`,{params: {password: password}})
            .then(res => {
                if(res.data !== false){
                    let user = res.data;
                    let userData = user.replace("{", "").replace("}","").split(",")
                    setAuth({
                        id: userData[0],
                        username: userData[1],
                        type: userData[2]
                    })
                    setShowSuccessMessage(true);
                    setHasLoginFailed(false);
                    console.log(from);
                    console.log(location.state)
                    console.log(                    setAuth({
                        id: userData[0],
                        username: userData[1],
                        type: userData[2]
                    }))
                    navigate(from, { replace: true });
                } else {
                    setHasLoginFailed(true);
                }
            });
    }

    return (
        <div>
            <h1>Login</h1>
            <div className="container">
                {hasLoginFailed && <div className="alert alert-warning">Invalid Credentials</div>}
                {showSuccessMessage && <div>Login Successful</div>}
                User Name: <input type="text" name="username" value={username} onChange={handleChange} />
                Password: <input type="password" name="password" value={password} onChange={handleChange} />
                <button className="btn btn-success" onClick={loginClicked}>Login</button>
            </div>
        </div>
    )
}

export default LoginSite;