"use strict";
const express = require("express");
const { spawn } = require("child_process");
const fs = require("fs");
const path = require("path");
const app = express();

const port = 3000;
const logMySQLFilePath = path.join(__dirname, "Logs", "MySQL.log");

app.get('/', (req, res) => {
    const interval = setInterval(() => {
        const process = spawn("node", ["./Scripts/mysql.js"]);

        process.stdout.on("data", (data) => {
            console.log(data.toString());
            const message = data.toString();

            fs.appendFileSync(logMySQLFilePath, message, (err) => {
                if(err) console.error(err);
                else console.log("Correct logged");
            });
        });

        process.stderr.on("data", (data) => {
            console.error(`Error: ${data}`);
        });

        process.on("close",(code) => {
            console.log(`Proceso terminado con código: ${code}`);
        });
    }, 3000);

    setTimeout(() => {
        clearInterval(interval);
        console.log('Intervalo detenido automáticamente después de 15s');
        res.json('Intervalo detenido automáticamente después de 15s');
    }, 15000);
});

app.listen(port, () => {
    console.log(`Server 1 listening on port ${port}`);
});