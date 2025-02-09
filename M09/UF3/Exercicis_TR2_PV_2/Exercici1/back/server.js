"use strict";
const express = require("express");
const { spawn } = require("child_process");
const http = require("http");
const { Server } = require("socket.io");
const cors = require("cors");

const app = express();
const server = http.createServer(app);

// Configurar CORS para permitir conexiones desde el frontend
app.use(cors());

const io = new Server(server, {
  cors: {
    origin: "*",
  },
});

const port = 3001;
let intervalo;

io.on("connection", (socket) => {
  console.log("A user connected", socket.id);

  socket.on("service1", (service) => {
    console.log("Mensaje recibido: ", service);

    // Detener cualquier intervalo en ejecución antes de iniciar uno nuevo
    if (intervalo) {
      clearInterval(intervalo);
      intervalo = null;
    }

    // Iniciar nuevo intervalo
    intervalo = setInterval(() => {
      const process = spawn("node", [`./Scripts/${service}.js`]);

      process.stdout.on("data", (data) => {
        console.log(data.toString());
        socket.emit("message", data.toString());
      });

      process.stderr.on("data", (data) => {
        console.error(`Error: ${data}`);
      });

      process.on("close", (code) => {
        console.log(`Proceso terminado con código: ${code}`);
      });
    }, 3000);

    // 🔹 Asegurar que el intervalo se detenga después de 15 segundos
    setTimeout(() => {
      if (intervalo) {
        clearInterval(intervalo);
        intervalo = null;
        console.log(`Intervalo detenido automáticamente después de 15s para el socket ${socket.id}`);
      }
    }, 15000);
  });

  // 🔹 Detener intervalo cuando el usuario se desconecta
  socket.on("disconnect", () => {
    if (intervalo) {
      clearInterval(intervalo);
      intervalo = null;
      console.log(`Intervalo eliminado porque el usuario se desconectó: ${socket.id}`);
    }
    console.log("User disconnected", socket.id);
  });
});

server.listen(port, () => {
  console.log(`Server 1 listening on port ${port}`);
});