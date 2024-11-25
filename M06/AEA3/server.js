const express = require('express');
const cors = require('cors');
const mysql = require('mysql2/promise');
const app = express();
const path = require('path');
const fs = require('fs');

// Middleware
app.use(cors({ origin: '*' }));
app.use(express.json());

const logPath = path.join(__dirname, 'Logs', 'Connections.log');
const logPath2 = path.join(__dirname, 'Logs', 'Requests.log');
const requestsPath = path.join(__dirname, 'Requests');

if(!fs.existsSync(requestsPath)) fs.mkdirSync('./Requests', { recursive: true });

const spainTime = new Intl.DateTimeFormat('es-ES', {
    timeZone: 'Europe/Madrid',
    dateStyle: 'short',
    timeStyle: 'long',
}).format(new Date());

function writeLogConnection(message) {
    fs.appendFileSync(logPath, message, (err) => {
        if (err) {
            console.error('Error writing to the connection log:', err);
        } else {
            console.log(`Logged: ${message}`);
        }
    });
}

async function createConnection() {
    try {
        const connection = await mysql.createConnection({
            host: 'localhost',
            user: 'root',
            password: '',
            database: 'preguntesesports',
            port: 3306
        });

        console.log("Connection established");

        const message = `[${spainTime}] Established connection to BD.\n`;
        writeLogConnection(message);

        return connection;
    } catch (err) {
        console.error('Connection error:', err);

        const message = `[${spainTime}] Error to establish connection to BD.\n`;
        writeLogConnection(message);
        
        throw err;
    }
}

function writeLogRequest(message) {
    fs.appendFileSync(logPath2, message, (err) => {
        if (err) {
            console.error('Error writing to the request log:', err);
        } else {
            console.log(`Logged: ${message}`);
        }
    });
}

app.get('/getQuestionsAnswers', async (req, res) => {
    const connection = await createConnection();
    
    try {
        const [results] = await connection.execute(`
            SELECT 
                p.id AS pregunta_id,
                p.enunciat,
                r.resposta
            FROM 
                preguntes p
            LEFT JOIN 
                respostes r
            ON 
                p.id = r.id_pregunta
        `);

        const response = { 
            "Preguntes": results.reduce((acc, row) => {
                let pregunta = acc.find(p => p.Enunciat === row.enunciat);

                if (!pregunta) {
                    pregunta = {
                        Enunciat: row.enunciat,
                        Respostes: []
                    };
                    acc.push(pregunta);
                }

                if (row.resposta) {
                    pregunta.Respostes.push(row.resposta);
                }

                return acc;
            }, [])
        };
        
        const now = new Date();
        const fileName = `preguntes_respostes_${now.getDate()}_${now.getMonth() + 1}_${now.getFullYear()}.json`;
        const filePath = path.join(requestsPath, fileName);

        fs.writeFileSync(filePath, JSON.stringify(response, null, 2));

        const message = `[${spainTime}] Successful processing request.\n`;
        writeLogRequest(message);

        res.json(response);
    } catch (err) {
        const message = `[${spainTime}] Error while processing request.\n`;
        writeLogRequest(message);

        console.error(err);
        res.status(500).json({ error: 'Internal Server Error' });
    } finally {
        connection.end();
    }
});

const PORT = 7000;
app.listen(PORT, () => {
    console.log(`Server listening on port ${PORT}`);
});