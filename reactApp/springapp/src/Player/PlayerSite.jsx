import React, { useEffect, useState } from 'react';
import Button from '@mui/material/Button';
import axios from 'axios';
import fileDownload from 'js-file-download';
import TextField from '@mui/material/TextField';


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
    }

    const findSound = () => {
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

    const uploadSound = () => {
        console.log(file)
        const formData = new FormData();
        formData.append('file', file);
        axios.post('http://localhost:8080/songs/upload', formData)
        .then(res => {
            console.log(res);
            console.log(res.data);
        })

    }


    return (
        <div className="App">
            <h3 className="mb-4">Play a mp3 file </h3>
            <TextField
                            label="Search"
                            variant="outlined"
                            size="small"
                            value={search}
                            onChange={handleSearch}
                        />
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={findSound}>Find</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={playSound}>Play</Button>
            <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={pauseSound}>Pause</Button>

            {/* <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={stopSound}>Stop</Button> */}
            {/* <Button variant="contained" style={{marginTop: "10px", marginRight: "10px"}} onClick={downloadSound}>Set</Button> */}

            {/* <label><input type="checkbox" checked={playInLoop} onChange={e => setPlayInLoop(e.target.checked)} /> Play in Loop</label> */}
        </div>
    );
}

export default PlayerSite;