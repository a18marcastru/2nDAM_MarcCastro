const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
require('dotenv').config();
const fileUpload = require('express-fileupload');
const path = require('path');
const fs = require('fs');

const app = express();
const spainTime = new Intl.DateTimeFormat('es-ES', {
  timeZone: 'Europe/Madrid',
  dateStyle: 'short',
  timeStyle: 'long',
}).format(new Date());
const logPath = path.join(__dirname, "logs.log");


// Middleware
app.use(express.json());
app.use(cors()); // Habilitar CORS per a qualsevol origen
app.use(fileUpload()); // Habilitar la pujada d'arxius

// Servir arxius estàtics des de la carpeta 'upload'
app.use('/upload', express.static(path.join(__dirname, 'upload')));
const motocicletesRoutes = require('./routes/motocicletes');
app.use('/api/motocicletes', motocicletesRoutes);
app.use(cors());


// Connexió a la base de dades MongoDB
mongoose.connect(process.env.MONGODB_URI, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
.then(() => {
  console.log('Connectat a MongoDB')

  const message = `[${spainTime}] Established connection to MongoDB.\n`;

  fs.appendFileSync(logPath, message, (err) => {
    if (err) {
      console.error('Error writing to the connection log:', err);
    } else {
      console.log(`Logged: ${message}`);
    }
  });
})
.catch((err) => { 
  console.error('Error al connectar a MongoDB', err);

  const message = `[${spainTime}] Error to establish connection to MongoDB.\n`;

  fs.appendFileSync(logPath, message, (err) => {
    if (err) {
      console.error('Error writing to the connection log:', err);
    } else {
      console.log(`Logged: ${message}`);
    }
  });
});

const closeDatabaseConnection = () => {
  mongoose.connection.close()
    .then(() => {
      console.log('Conexió a MongoDB tancada correctament');

      const message = `[${spainTime}] Connection to MongoDB closed.\n`;

      fs.appendFileSync(logPath, message, (err) => {
        if (err) {
          console.error('Error writing to the connection log:', err);
        } else {
          console.log(`Logged: ${message}`);
        }
      });
      process.exit(0);
    })
    .catch((err) => {
      console.error('Error al cerrar la conexión con MongoDB:', err);
      process.exit(1);
    });
};

process.on('SIGINT', closeDatabaseConnection);

// Iniciar el servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor en funcionament a http://localhost:${PORT}`);
});

// Rutes
app.get('/', (req, res) => {
  res.send('Benvingut al servidor de motocicletes');
});