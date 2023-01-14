import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import sound from './metallica.mp3';


function PlayerSite() {

    const audioTune = new Audio(sound);

    const [playInLoop, setPlayInLoop] = useState(false);

    useEffect(() => {
        audioTune.load();
    }, [])

    useEffect(() => {
        audioTune.loop = playInLoop;
    }, [playInLoop])

    const playSound = () => {
        audioTune.play();
    }

    const pauseSound = () => {
        audioTune.pause();
    }

    const stopSound = () => {
        audioTune.pause();
        audioTune.currentTime = 0;
    }

    return (
        <div className="App">
            <h3 className="mb-4">Play a mp3 file </h3>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={playSound}>Play</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={pauseSound}>Pause</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={stopSound}>Stop</Button>

            <label><input type="checkbox" checked={playInLoop} onChange={e => setPlayInLoop(e.target.checked)} /> Play in Loop</label>
        </div>
    );
}

export default PlayerSite;