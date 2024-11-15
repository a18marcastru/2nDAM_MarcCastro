"use strict";
const express = require('express');
const { spawn } = require('child_process');
const { createServer } = require('node:http');
const { Server } = require('socket.io');
const fs = require('fs');
const path = require('path');
const app = express();
const port = 3010;

app.use(express.json())
const server = createServer(app);
const io = new Server(server);
const scriptsFolder = path.join(__dirname, 'scripts');

io.on('connection', (socket) => {
    console.log('a user connected', socket.id);
    socket.on('disconnect', () => {
        console.log('user disconnected', socket.id);
    });
});

app.get('/processes', (req, res) => {
    fs.readdir(scriptsFolder, (err, files) => {
        if (err) {
            console.log('Error al leer la carpeta:', err);
            return;
        }
    
        const jsFiles = files.filter(file => file.endsWith('.js'));
    
        console.log('Archivos .js en la carpeta scripts:');
        jsFiles.forEach(file => {
            console.log(file);
        });
        const response = {
            procesos: jsFiles.map(file => ({
                title: file,
                button1: "Start",
                button2: "Log",
                button3: "LogError" 
            }))
        };

        res.json(response);
    });
})

app.post('/startFunctions', (req, res) => {
    const { title } = req.body;
    console.log(title)

    const activation = spawn('node', [`./scripts/${title}`]);
    
    io.emit("Activation", { title });

    activation.stdout.on('data', (data) => {
        console.log(`${data}`);
        io.emit("Logs", data)
    });

    activation.stderr.on('data', (data) => {
        console.error(`Error del script: ${data}`);
    });

    activation.on('close', (code) => {
        console.log(`Proceso terminado con código: ${code}`);
    });
});

server.listen(port, () => {
    console.log(`localhost:${port}`);
});