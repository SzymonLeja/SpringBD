import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import axios from 'axios';
import fileDownload from 'js-file-download';
import TextField from '@mui/material/TextField';
import DynamicSearch from '../components/DynamicSearch';


function PlayerSite() {

    const audioCtx = new (window.AudioContext || window.webkitAudioContext)();
    let source = audioCtx.createBufferSource();
    const [search, setSearch] = useState('');
    const [file, setFile] = useState('');

    useEffect(() => {
        axios.get('http://localhost:8080/songs/metallica', { responseType: 'arraybuffer' })
        .then(res => {
            audioCtx.decodeAudioData(res.data, (buffer) => {
                source.buffer = buffer;
                source.connect(audioCtx.destination);
            });
            source.start();
            audioCtx.suspend();
        })
        .catch(err => {
            console.log(err);
        }
        )
    }, [])

    const playSound = () => {
        audioCtx.resume();
    }

    const pauseSound = () => {
        audioCtx.suspend();
    }

    const handleSearch = (e) => {   
        setSearch(e.target.value);
        audioCtx.suspend();

    }

    const findSound = () => {
        audioCtx.suspend();
        source.disconnect();
        source = audioCtx.createBufferSource();
        axios.get('http://localhost:8080/songs/' + search, { responseType: 'arraybuffer' })
        .then(res => {
            audioCtx.decodeAudioData(res.data, (buffer) => {
                source.buffer = buffer;
                source.connect(audioCtx.destination);
            });
            source.start();
            audioCtx.suspend();
        })
        .catch(err => {
            console.log(err);
        }
        )
    }



    return (
        <div className="App">
            <DynamicSearch />
            {/* <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={stopSound}>Stop</Button> */}
            {/* <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={downloadSound}>Set</Button> */}

            {/* <label><input type="checkbox" checked={playInLoop} onChange={e => setPlayInLoop(e.target.checked)} /> Play in Loop</label> */}
        </div>
    );
}

export default PlayerSite;