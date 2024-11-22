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
const logsFolder = path.join(__dirname, 'Logs');
const logsErrorFolder = path.join(__dirname, 'LogsError');

io.on('connection', (socket) => {
    console.log('A user connected', socket.id);
    socket.on('disconnect', () => {
        console.log('User disconnected', socket.id);
    });
});

app.get('/getMicroservices', (req, res) => {
    fs.readdir(scriptsFolder, { withFileTypes: true }, (err, dirs) => {
        if (err) {
            console.log('Error reading the folder:', err);
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
});

function startMicroservice(title, newActivated) {
    const logPath = path.join(logsFolder, `${title}.log`);
    const message = `[${new Date().toISOString()}] ${title} Activated.\n`;

    io.emit("Activation", { title, activated: newActivated });
    
    fs.appendFile(logPath, message, (err) => {
        if (err) {
            console.error(`Error writing to the log of ${title}:`, err);
        } else {
            console.log(`Logged: ${message}`);
        }
    });
}

function stopMicroservice(title, newActivated) {
    const logPath = path.join(logsFolder, `${title}.log`);
    const message = `[${new Date().toISOString()}] ${title} Disabled.\n`;

    io.emit("Activation", { title, activated: newActivated });
    
    fs.appendFile(logPath, message, (err) => {
        if (err) {
            console.error(`Error writing to the log of ${title}:`, err);
        } else {
            console.log(`Logged: ${message}`);
        }
    });
}

app.post('/postFunctions', (req, res) => {
    const { title, activated } = req.body;

    const activation = spawn('node', [`./Scripts/${title}/index.js`]);
    
    const newActivated = activated === 0 ? 1 : 0;

    activation.stdout.on('data', (data) => {
        if (newActivated === 1) startMicroservice(title, newActivated);
    });

    activation.stderr.on('data', (data) => {
        const logPath = path.join(logsErrorFolder, `${title}.log`);
        const message = `[${new Date().toISOString()}] ${title} Error executing the script.\n`;

        io.emit("Activation", { title, activated: 0 });

        fs.appendFile(logPath, message, (err) => {
            if (err) {
                console.error(`Error writing to the error log of ${title}:`, err);
            } else {
                console.log(`Logged: ${message}`);
            }
        });
        console.error(`Script error: ${data}`);
    });

    activation.on('close', (code) => {
        if (newActivated === 0) stopMicroservice(title, newActivated);
    });
});

app.post('/postLogs', (req, res) => {
    const { title } = req.body;

    fs.readFile(`${logsFolder}/${title}.log`, 'utf-8', (err, data) => {
        if (err) console.error("Error reading the log file");
        const logs = data.split('\n').filter(line => line.trim() !== '').map(line => ({ log: line }));
        
        res.json({ logs });
    });
});

app.post('/postLogsError', (req, res) => {
    const { title } = req.body;

    fs.readFile(`${logsErrorFolder}/${title}.log`, 'utf-8', (err, data) => {
        if (err) console.error("Error reading the error log file");
        const logs = data.split('\n').filter(line => line.trim() !== '').map(line => ({ log: line }));
        
        res.json({ logs });
    });
});

server.listen(port, () => {
    console.log(`localhost:${port}`);
});