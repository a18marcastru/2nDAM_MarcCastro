const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const fileUpload = require('express-fileupload');
const path = require('path');

const app = express();

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
mongoose.connect('mongodb+srv://a18marcastru:mongodb@cluster24-25.38noo.mongodb.net/motocicletes', {
  useNewUrlParser: true,
  useUnifiedTopology: true,
})
.then(() => console.log('Connectat a MongoDB'))
.catch((err) => console.error('Error al connectar a MongoDB', err));

// Iniciar el servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor en funcionament a http://localhost:${PORT}`);
});

// Rutes
app.get('/', (req, res) => {
  res.send('Benvingut al servidor de motocicletes');
});