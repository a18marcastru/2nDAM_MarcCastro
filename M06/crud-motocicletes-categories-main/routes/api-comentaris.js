import express from 'express';
import { Motocicleta, Categoria, Comentari } from '../models/index.js';

const router = express.Router();

const calendar = () => {
  return new Date();
}

// Llista tots els comentaris
router.get('/', async (req, res) => {
  try {
    const comentaris = await Comentari.findAll();
    res.json(comentaris);
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Crear un nou comentari
router.post('/', async (req, res) => {
  try {
    const { motoId, comentari, usuari } = req.body;
    const calendarDate = calendar();
    await Comentari.create({ motoId, comentari, usuari, calendar: calendarDate });
    res.status(201).json("Nuevo comentario añadido");
  } catch (error) {
    res.status(500).send(error.message);
  }
});
/*
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
*/


export default router;