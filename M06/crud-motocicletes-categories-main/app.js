import express from 'express';
import path from 'path';
import { fileURLToPath } from 'url';
import { sequelize } from './models/index.js'; // Ahora importamos correctamente
import motocicletesRouter from './routes/motocicletes.js';
import categoriesRouter from './routes/categories.js';
import comentarisRouter from './routes/comentaris.js';
import apimotocicletes from './routes/api-motocicletes.js';
import apicomentaris from './routes/api-comentaris.js';

const app = express();
const PORT = process.env.PORT || 3000;

// Configuración de rutas y vistas
const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

app.set('view engine', 'ejs');
app.set('views', path.join(__dirname, 'views'));
app.use(express.urlencoded({ extended: true }));
app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

// Rutas
app.use('/motocicletes', motocicletesRouter);
app.use('/categories', categoriesRouter);
app.use('/comentaris', comentarisRouter);
app.use('/api/motocicletes', apimotocicletes);
app.use('/api/comentaris', apicomentaris);

// Página de bienvenida
app.get('/', (req, res) => {
  res.render('portada');
});

// Sincronización de la base de datos
sequelize.sync()
  .then(() => {
    console.log('Base de datos sincronizada.');
    app.listen(PORT, () => {
      console.log(`Servidor funcionando en http://localhost:${PORT}`);
    });
  })
  .catch(err => console.error('Error sincronizando la base de datos:', err));

  