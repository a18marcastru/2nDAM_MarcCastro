"use strict";
const express = require('express');
const { spawn } = require('child_process');
const { createServer } = require('node:http');
const { Server } = require('socket.io');
const fs = require('fs');
const path = require('path');
const app = express();
const port = 3000;

app.use(express.json())
const server = createServer(app);
const io = new Server(server);
const scriptsFolder = path.join(__dirname, 'Scripts');

io.on('connection', (socket) => {
    console.log('a user connected', socket.id);
    socket.on('disconnect', () => {
        console.log('user disconnected', socket.id);
    });
});

app.get('/getMicroservices', (req, res) => {
    fs.readdir(scriptsFolder, { withFileTypes: true }, (err, dirs) => {
        if (err) {
            console.log('Error al leer la carpeta:', err);
            return;
        }

        const directories = dirs.filter(item => item.isDirectory()).map(dir => dir.name);

        const response = {
            microservices: directories.map(directories => ({
                title: directories,
                button1: "Start",
                button2: "Log",
                button3: "LogError",
                activated: 0
            }))
        };

        res.json(response);
    });
})

function startMicroservice(title) {
    const logPath = path.join(__dirname, 'Logs', `${title}.log`);
    const message = `[${new Date().toISOString()}] ${title} Activated.\n`;
    
    fs.appendFile(logPath, message, (err) => {
        if (err) {
            console.error(`Error al escribir en el log de ${title}:`, err);
        } else {
            console.log(`Registrado en log: ${message}`);
        }
    });
}

function stopMicroservice(title) {
    const logPath = path.join(__dirname, 'logs', `${title}.log`);
    const message = `[${new Date().toISOString()}] ${title} Desactivated.\n`;
    
    fs.appendFile(logPath, message, (err) => {
        if (err) {
            console.error(`Error al escribir en el log de ${title}:`, err);
        } else {
            console.log(`Registrado en log: ${message}`);
        }
    });
}

app.post('/postFunctions', (req, res) => {
    const { title, activated } = req.body;
    console.log(title + " " + activated)

    const activation = spawn('node', [`./Scripts/${title}/index.js`]);
    
    const newActivated = activated === 0? 1 : 0;

    io.emit("Activation", { title, activated: newActivated });

    activation.stdout.on('data', (data) => {
        if(activated === 1) startMicroservice(title)
        console.log(`${data}`);
    });

    activation.stderr.on('data', (data) => {
        console.error(`Error del script: ${data}`);
    });

    activation.on('close', (code) => {
        if(activated === 0) stopMicroservice(title)
        console.log(`Proceso terminado con código: ${code}`);
    });
});

app.post('/postLogs', (req, res) => {
    const { title } = req.body;

    console.log(title);
});

server.listen(port, () => {
    console.log(`localhost:${port}`);
});