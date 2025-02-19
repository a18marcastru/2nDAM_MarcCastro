"use strict";
const express = require("express");
const { spawn } = require("child_process");
const app = express();

const port = 3000;
let intervalID;

app.get('/start', (req, res) => {
    intervalID = setInterval(() => {
        const process = spawn("node", ["./script.js"]);

        process.stdout.on("data", (data) => {
            console.log(data.toString());
        });

        process.stderr.on("data", (data) => {
            console.error(`Error: ${data}`);
        });

        process.on("close",(code) => {
            console.log(`Proceso terminado con código: ${code}`);
        });

    }, 3000);

    res.json("Interval executed");
});

app.post('/stop', (req, res) => {
    clearInterval(intervalID);
    console.log("Interval stoped");
    res.json("Interval stoped");
});

app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
});