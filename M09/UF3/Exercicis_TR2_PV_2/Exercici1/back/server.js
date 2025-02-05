"use strict";
const express = require("express");
const { spawn } = require('child_process');
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
let intervalo;

io.on('connection', (socket) => {
  console.log('A user connected', socket.id);

  socket.on("service1", (service) => {
    console.log("Mensaje: ", service);

    if (intervalo) {
      clearInterval(intervalo);
    }

    intervalo = setInterval(() => {

      const process = spawn('node', [`./Scripts/${service}.js`]);

      process.stdout.on('data', (data) => {
        console.log(data.toString());

        socket.emit("message", data.toString());
      });

      process.stderr.on('data', (data) => {
        console.error(`Error: ${data}`);
      });

      process.on('close', (code) => {
        console.log(`Proceso terminado con código: ${code}`);
      });
    }, 3000); 
  });

  setTimeout(() => {
    clearInterval(intervalo);
    console.log('Se detuvo la ejecución.');
  }, 15000);

  socket.on('disconnect', () => {
    console.log('User disconnected', socket.id);
  });
});

server.listen(port, () => {
  console.log(`Server 1 listening port ${port}`);
});