import express from 'express';
import { Motocicleta, Categoria } from '../models/index.js';

const router = express.Router();

// Llista totes les motocicletes
router.get('/', async (req, res) => {
  try {
    const motos = await Motocicleta.findAll();
    res.json(motos);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Crear una nova motocicleta
router.post('/', async (req, res) => {
  try {
    const { marca, model, descripcio, potencia, categoriaId } = req.body;
    await Motocicleta.create({ marca, model, descripcio, potencia, categoriaId });
    res.status(201).json("Nueva moto añadida");
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Mostrar els detalls d'una motocicleta
router.get('/:id', async (req, res) => {
  try {
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      res.json(moto);
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Actualitzar una motocicleta
router.put('/:id', async (req, res) => {
  try {
    const { marca, model, descripcio, potencia, categoriaId } = req.body;
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      await moto.update({ marca, model, descripcio, potencia, categoriaId });
      res.status(201).json("Moto actualizada");
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Eliminar una motocicleta
router.delete('/:id/eliminar', async (req, res) => {
  try {
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      await moto.destroy();
      res.status(201).json("Moto eliminada");
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

export default router;