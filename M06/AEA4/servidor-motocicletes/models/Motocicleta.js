const mongoose = require('mongoose');

const MotocicletaSchema = new mongoose.Schema({
  marca: { type: String, required: true },
  model: { type: String, required: true },
  potencia: { type: Number, required: true },
  descripcio: { type: String, required: true },
  imatge: { type: String }, // Ruta de la imatge pujada
}, {
  timestamps: true,
});

module.exports = mongoose.model('Motocicleta', MotocicletaSchema);