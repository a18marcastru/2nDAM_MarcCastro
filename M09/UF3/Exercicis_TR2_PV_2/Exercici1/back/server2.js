"use strict";
const express = require('express');
const app = express();
const { spawn } = require('child_process');
const { createServer } = require('node:http');
const { Server } = require('socket.io');
const fs = require('fs');
const path = require('path');

const server = createServer(app);
const io = new Server(server);
const port = 3000;

io.on('connection', (socket) => {
    console.log('A user connected', socket.id);

    io.on("service2", ({ service, execute }) => {
        
    });

    socket.on('disconnect', () => {
        console.log('User disconnected', socket.id);
    });
});

app.listen(port, () => {
    console.log(`Server 1 listening port ${port}`);
});