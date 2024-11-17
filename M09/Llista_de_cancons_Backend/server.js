"use strict";
const express = require('express');
const { createServer } = require('node:http');
const { Server } = require('socket.io');
const app = express();
const path = require('path');
const fs = require('fs');
const port = 3010;

app.use(express.json())
const server = createServer(app);
const io = new Server(server);

let musics;
const musicPath = path.join(__dirname, 'musics.json');

io.on('connection', (socket) => {
    console.log('a user connected', socket.id);
    socket.on('disconnect', () => {
        console.log('user disconnected', socket.id);
    });
});

app.get('/musics', (req, res) => {
    console.log("Hola")
    fs.readFile(musicPath, 'utf8', (err, data) => {
        if(err) return res.status(500).json({ message: 'Error al leer el archivo JSON' });

        musics = JSON.parse(data);

        res.json(musics)
    })
});

app.post('/newMusic', (req, res) => {
    const { title } = req.body;

    console.log(title)

    fs.readFile(musicPath, 'utf8', (err, data) => {
        let musicsData = JSON.parse(data);

        const lastMusic = musicsData.musics[musicsData.musics.length - 1];
        const newId = lastMusic ? lastMusic.id + 1 : 1;

        const newMusic = {
            id: newId,
            name: title
        };

        musicsData.musics.push(newMusic);

        fs.writeFile(musicPath, JSON.stringify(musicsData, null, 2), (writeErr) => {
            if (writeErr) {
                console.error('Error al escribir en el archivo:', writeErr);
                return res.status(500).json({ message: 'Error al guardar la música' });
            }

            console.log('Nueva música añadida:', newMusic);
            io.emit('newMusic', JSON.stringify(newMusic));
            res.status(201).json({ message: 'Música añadida exitosamente', music: newMusic });
        });
    });
});

app.put('/updateMusic/:id', (req, res) => {
    const id = parseInt(req.params.id)
    const { title } = req.body;

    console.log(title)

    fs.readFile(musicPath, 'utf8', (err, data) => {
        let musicsData = JSON.parse(data);
        
        musicsData.musics.forEach(element => {
            if(element.id === id) element.name = title
        });

        const updateMusic = {
            id: id,
            name: title
        }

        console.log(musicsData)

        fs.writeFile(musicPath, JSON.stringify(musicsData, null, 2), (writeErr) => {
            if (writeErr) {
                console.error('Error al escribir en el archivo:', writeErr);
                return res.status(500).json({ message: 'Error al guardar la música' });
            }

            io.emit('updateMusic', JSON.stringify(updateMusic));
            res.status(200).json({ message: 'Música actualizada', id });
        });
    });
});

app.delete('/deleteMusic/:id', (req, res) => {
    const id = parseInt(req.params.id)

    console.log(id)

    fs.readFile(musicPath, 'utf8', (err, data) => {
        let musicsData = JSON.parse(data);

        const newListMusics = musicsData.musics.filter(music => music.id !== id);

        musicsData.musics = newListMusics;
        console.log(musicsData)

        fs.writeFile(musicPath, JSON.stringify(musicsData, null, 2), (writeErr) => {
            if (writeErr) {
                console.error('Error al escribir en el archivo:', writeErr);
                return res.status(500).json({ message: 'Error al guardar la música' });
            }

            io.emit('deleteMusic', id);
            res.status(200).json({ message: 'Música eliminada exitosamente', id });
        });
    });
});

server.listen(port, () => {
    console.log(`localhost:${port}`);
});