import { io } from "socket.io-client";

const socket = io("http://localhost:3002");
var count = 1;

export const funcionSocket2 = (service) => {
    if(socket.connected) {
        socket.emit("service2", service);
    }
    else console.error("No");
}

socket.on("message2", (msg) => {
    console.log(`Message ${count++}: `, msg);
});