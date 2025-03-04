import express from 'express';
import { Motocicleta, Categoria, Comentari } from '../models/index.js';
import motocicleta from '../models/motocicleta.js';

const router = express.Router();
const calendar = () => {
  return new Date();
}

router.get('/nou', async (req, res) => {
  try {
    const motocicletes = await Motocicleta.findAll();
    res.render('comentaris/nou', { motocicletes });
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Mostrar els detalls d'una motocicleta
router.get('/:id', async (req, res) => {
  try {
    const comens = await Comentari.findAll({
      where: { motoId: req.params.id}
    });
    const moto = await Motocicleta.findOne({
      where: { id: req.params.id}
    });
    res.render('comentaris/index', { comentaris: comens, motocicleta: moto });
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Crear una nova motocicleta
router.post('/', async (req, res) => {
  try {
    const { motoId, comentari, usuari } = req.body;
    const calendarDate = calendar();
    await Comentari.create({ motoId, comentari, usuari, calendar: calendarDate });
    res.redirect('/comentaris/index');
  } catch (error) {
    res.status(500).send(error.message);
  }
});

/*
// Formulari per afegir una nova motocicleta (inclou la llista de categories)
router.get('/nou', async (req, res) => {
  try {
    const categories = await Categoria.findAll();
    res.render('motocicletes/nou', { categories });
  } catch (error) {
    res.status(500).send(error.message);
  }
});



// Mostrar els detalls d'una motocicleta
router.get('/:id', async (req, res) => {
  try {
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      res.render('motocicletes/mostrar', { motocicleta: moto });
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Formulari per editar una motocicleta (inclou la llista de categories)
router.get('/:id/editar', async (req, res) => {
  try {
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      const categories = await Categoria.findAll();
      res.render('motocicletes/editar', { motocicleta: moto, categories });
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Actualitzar una motocicleta
router.post('/:id', async (req, res) => {
  try {
    const { marca, model, descripcio, potencia, categoriaId } = req.body;
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      await moto.update({ marca, model, descripcio, potencia, categoriaId });
      res.redirect('/motocicletes');
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Eliminar una motocicleta
router.post('/:id/eliminar', async (req, res) => {
  try {
    const moto = await Motocicleta.findByPk(req.params.id);
    if (moto) {
      await moto.destroy();
      res.redirect('/motocicletes');
    } else {
      res.status(404).send('Motocicleta no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});
*/

export default router;