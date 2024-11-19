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
const logPath2 = path.join(__dirname, 'Logs', 'File_creation.log');

function createConnection() {
    return mysql.createConnection({
        host: 'localhost',
        user: 'root',
        password: '',
        database: 'preguntesesports',
        port: 3306
    })
        .then(connection => {
            console.log("Connexió creada");

            const message = `[${new Date().toISOString()}] Established connection to BD.\n`;
            fs.appendFile(logPath, message, (err) => {
                if (err) {
                    console.error(`Error al escribir en el log de conexión:`, err);
                } else {
                    console.log(`Registrado en log: ${message}`);
                }
            });

            return connection;
        })
        .catch(err => {
            console.error('Error de connexió: ' + err);

            const message = `[${new Date().toISOString()}] Failed to establish connection to BD.\n`;
            fs.appendFile(logPath, message, (err) => {
                if (err) {
                    console.error(`Error al escribir en el log de conexión:`, err);
                } else {
                    console.log(`Registrado en log: ${message}`);
                }
            });

            throw err;
        });
}

app.get('/getQuestionsAnswers', async (req, res) => {
    let connection;
    try {
        connection = await createConnection();

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

        console.log(results)

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
        
        const message = `[${new Date().toISOString()}] Successful processing request.\n`;
        fs.appendFile(logPath2, message, (err) => {
            if (err) {
                console.error(`Error al escribir en el log de conexión:`, err);
            } else {
                console.log(`Registrado en log: ${message}`);
            }
        });

        res.json(response);
    } catch (err) {
        const message = `[${new Date().toISOString()}] Error while processing request.\n`;
        fs.appendFile(logPath2, message, (err) => {
            if (err) {
                console.error(`Error al escribir en el log de conexión:`, err);
            } else {
                console.log(`Registrado en log: ${message}`);
            }
        });

        console.error(err);
        res.status(500).json({ error: 'Internal Server Error' });
    } finally {
        if (connection) {
            await connection.end();
        }
    }
});

// Arrancar el servidor
const PORT = 7000; // Port del servei
app.listen(PORT, () => {
    console.log(`Servidor escoltant al port ${PORT}`);
});