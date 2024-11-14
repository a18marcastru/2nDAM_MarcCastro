"use strict";
const express = require('express');
const { spawn } = require('child_process');
const { createServer } = require('node:http');
const { Server } = require('socket.io');
const app = express();
const port = 3010;

app.use(express.json())
const server = createServer(app);
const io = new Server(server);
// const io = socketIo(server, {
//     cors: {
//         origin: ["http://localhost:3010"],
//         methods: ["POST"]
//     }
// });

io.on('connection', (socket) => {
    console.log('a user connected', socket.id);
    socket.on('disconnect', () => {
        console.log('user disconnected', socket.id);
    });
});

app.post('/startFunctions', (req, res) => {
    const { title } = req.body;
    console.log(title)

    if(title === 'APIs') {
        const activation = spawn('node', ['./scripts/api.js']);

        activation.stdout.on('data', (data) => {
            console.log(`${data}`);
        })
    }
    else if(title == 'MongoDB') {
        const activation = spawn('node', ['./scripts/mongo.js']);

        activation.stdout.on('data', (data) => {
            console.log(`${data}`);
        })
    }
    else {
        const activation = spawn('node', ['./scripts/mysql.js']);

        activation.stdout.on('data', (data) => {
            console.log(`${data}`);
        })
    }
});

server.listen(port, () => {
    console.log(`localhost:${port}`);
});