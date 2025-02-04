import { io } from "socket.io-client";

const socket = io("http://localhost:3001");

export const funcionSocket = (service) => {
    if(socket.connected) {
        socket.emit("service1", service);
    }
    else console.error("No");
}