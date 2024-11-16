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

app.get('musics', (req, res) => {
    fs.readFile('musics.json', 'utf8', (err, data) => {
        if(err) return res.status(500).json({ message: 'Error al leer el archivo JSON' });

        musics = JSON.parse(data);

        res.json(musics)
    })
});

server.listen(port, () => {
    console.log(`localhost:${port}`);
});