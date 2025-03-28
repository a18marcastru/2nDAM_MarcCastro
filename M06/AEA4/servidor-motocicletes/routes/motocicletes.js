const express = require('express');
const router = express.Router();
const path = require('path');
const fs = require('fs');
const Motocicleta = require('../models/Motocicleta');

const logPath = path.join(__dirname, '..', 'logs.log');
const spainTime = new Intl.DateTimeFormat('es-ES', {
  timeZone: 'Europe/Madrid',
  dateStyle: 'short',
  timeStyle: 'long',
}).format(new Date());

// Crear una nova motocicleta
router.post('/', async (req, res) => {
  try {
    let imatgePath = '';

    // Gestionar la pujada de la imatge
    if (req.files && req.files.imatge) {
      const imatge = req.files.imatge;
      const uploadPath = path.join(__dirname, '..', 'upload', imatge.name);

      await imatge.mv(uploadPath);
      imatgePath = `/upload/${imatge.name}`;
    }

    const novaMoto = new Motocicleta({
      marca: req.body.marca,
      model: req.body.model,
      potencia: req.body.potencia,
      descripcio: req.body.descripcio,
      imatge: imatgePath,
    });

    const motoGuardada = await novaMoto.save();

    const message = `[${spainTime}] New motorcycle saved.\n`;

    fs.appendFileSync(logPath, message, (err) => {
      if (err) {
        console.error('Error writing to the connection log:', err);
      } else {
        console.log(`Logged: ${message}`);
      }
    });
    
    res.status(201).json(motoGuardada);
  } catch (error) {
    res.status(500).json({ missatge: 'Error al crear la motocicleta', error });
  }
});

// Obtenir totes les motocicletes
router.get('/', async (req, res) => {
  try {
    const motos = await Motocicleta.find();
    res.json(motos);
  } catch (error) {
    res.status(500).json({ missatge: 'Error al obtenir les motocicletes', error });
  }
});

// Obtenir una motocicleta per ID
router.get('/:id', async (req, res) => {
  try {
    const moto = await Motocicleta.findById(req.params.id);
    if (!moto) return res.status(404).json({ missatge: 'Motocicleta no trobada' });
    res.json(moto);
  } catch (error) {
    res.status(500).json({ missatge: 'Error al obtenir la motocicleta', error });
  }
});

// Actualitzar una motocicleta per ID
router.put('/:id', async (req, res) => {
  try {
    let imatgePath = req.body.imatge || '';

    // Gestionar la pujada de la imatge si se'n proporciona una de nova
    if (req.files && req.files.imatge) {
      const imatge = req.files.imatge;
      const uploadPath = path.join(__dirname, '..', 'upload', imatge.name);

      await imatge.mv(uploadPath);
      imatgePath = `/upload/${imatge.name}`;
    }

    const motoActualitzada = await Motocicleta.findByIdAndUpdate(
      req.params.id,
      {
        marca: req.body.marca,
        model: req.body.model,
        potencia: req.body.potencia,
        descripcio: req.body.descripcio,
        imatge: imatgePath,
      },
      { new: true }
    );

    if (!motoActualitzada) return res.status(404).json({ missatge: 'Motocicleta no trobada' });

    res.json(motoActualitzada);
  } catch (error) {
    res.status(500).json({ missatge: 'Error al actualitzar la motocicleta', error });
  }
});

// Eliminar una motocicleta per ID
router.delete('/:id', async (req, res) => {
  try {
    const motoEliminada = await Motocicleta.findByIdAndDelete(req.params.id);
    if (!motoEliminada) return res.status(404).json({ missatge: 'Motocicleta no trobada' });
    res.json({ missatge: 'Motocicleta eliminada' });
  } catch (error) {
    res.status(500).json({ missatge: 'Error al eliminar la motocicleta', error });
  }
});

module.exports = router;