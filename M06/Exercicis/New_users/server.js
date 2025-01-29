const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const path = require('path');

const app = express();

app.use(express.json());
app.use(cors());

const usersRoutes = require('./routes/users');
app.use('/api/users', usersRoutes);

mongoose.connect('mongodb+srv://a18marcastru:mongodb@cluster24-25.38noo.mongodb.net/users', {
    useNewUrlParser: true,
    useUnifiedTopology: true,
})
.then(() => console.log('Connectat a MongoDB'))
.catch((err) => console.error('Error al connectar a MongoDB', err));

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor en funcionament a http://localhost:${PORT}`);
});