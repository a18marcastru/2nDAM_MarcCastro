"use strict";
const express = require("express");
const http = require("http");
const { Server } = require("socket.io");
const cors = require("cors");

const app = express();
const server = http.createServer(app);

// Configurar CORS para permitir conexiones desde el frontend
app.use(cors()); // Asegúrate de usar el puerto correcto de Vue

const io = new Server(server, {
  cors: {
    origin: "*", // Permite conexiones desde Vue.js
  },
});

const port = 3001;

io.on('connection', (socket) => {
    console.log('A user connected', socket.id);

    socket.on("service1", (service) => {
        console.log("Mensaje: ", service);

        
    });

    socket.on('disconnect', () => {
        console.log('User disconnected', socket.id);
    });
});

server.listen(port, () => {
    console.log(`Server 1 listening port ${port}`);
});