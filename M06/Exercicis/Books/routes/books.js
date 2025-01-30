const express = require('express');
const router = express.Router();
const path = require('path');
const Book = require('../models/Book');
const { title } = require('process');

router.post('/', async (req, res) => {
    try {
      const newBook = new Book({
        title: req.body.title,
        autor: req.body.autor,
        year: req.body.year,
        genere: req.body.genere
      });
  
      const bookSave = await newBook.save();
      res.status(201).json(bookSave);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al crear el llibre', error });
    }
});

router.get('/', async (req, res) => {
    try {
      const books = await Book.find();
      res.json(books);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al obtenir els llibres', error });
    }
});

router.get('/:id', async (req, res) => {
    try {
      const book = await Book.findById(req.params.id);
      if (!book) return res.status(404).json({ missatge: 'Llibre no trobada' });
      res.json(book);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al obtenir el llibre', error });
    }
});

router.put('/:id', async (req, res) => {
    try {
      const bookUpdate = await Book.findByIdAndUpdate(
        req.params.id,
        {
          title: req.body.title,
          autor: req.body.autor,
          year: req.body.year,
          genere: req.body.genere
        },
        { new: true }
      );
  
      if (!bookUpdate) return res.status(404).json({ missatge: 'Llibre no trobada' });
  
      res.json(bookUpdate);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al actualitzar el llibre', error });
    }
});

router.delete('/:id', async (req, res) => {
    try {
      const bookDelete = await Book.findByIdAndDelete(req.params.id);
      if (!bookDelete) return res.status(404).json({ missatge: 'Llibre no trobada' });
      res.json({ missatge: 'Llibre eliminada' });
    } catch (error) {
      res.status(500).json({ missatge: 'Error al eliminar el llibre', error });
    }
});

module.exports = router;