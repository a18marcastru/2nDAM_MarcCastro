import express from 'express';
import { Categoria, Motocicleta } from '../models/index.js';

const router = express.Router();

// Llista totes les categories amb les motocicletes associades
router.get('/', async (req, res) => {
  try {
    const categories = await Categoria.findAll({
      include: Motocicleta
    });
    res.render('categories/index', { categories });
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Formulari per crear una nova categoria
router.get('/nou', (req, res) => {
  res.render('categories/nou');
});

// Crear una nova categoria
router.post('/', async (req, res) => {
  try {
    const { nombre } = req.body;
    await Categoria.create({ nombre });
    res.redirect('/categories');
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Formulari per editar una categoria
router.get('/:id/editar', async (req, res) => {
  try {
    const categoria = await Categoria.findByPk(req.params.id);
    if (categoria) {
      res.render('categories/editar', { categoria });
    } else {
      res.status(404).send('Categoría no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Actualitzar una categoria
router.post('/:id', async (req, res) => {
  try {
    const { nombre } = req.body;
    const categoria = await Categoria.findByPk(req.params.id);
    if (categoria) {
      await categoria.update({ nombre });
      res.redirect('/categories');
    } else {
      res.status(404).send('Categoría no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

// Eliminar una categoria
router.post('/:id/eliminar', async (req, res) => {
  try {
    const categoria = await Categoria.findByPk(req.params.id);
    if (categoria) {
      await categoria.destroy();
      res.redirect('/categories');
    } else {
      res.status(404).send('Categoría no trobada');
    }
  } catch (error) {
    res.status(500).send(error.message);
  }
});

export default router;
