const express = require('express');
const router = express.Router();
const path = require('path');
const User = require('../models/User');

router.post('/', async (req, res) => {
    try {
      const newUser = new User({
        email: req.body.email,
        password: req.body.password,
        name: req.body.name,
        username: req.body.username
      });
  
      const userSave = await newUser.save();
      res.status(201).json(userSave);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al crear el usuari', error });
    }
});

router.get('/', async (req, res) => {
    try {
      const users = await User.find();
      res.json(users);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al obtenir els usuaris', error });
    }
});

router.get('/:id', async (req, res) => {
    try {
      const user = await User.findById(req.params.id);
      if (!user) return res.status(404).json({ missatge: 'Usuari no trobada' });
      res.json(user);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al obtenir el usuari', error });
    }
});

router.put('/:id', async (req, res) => {
    try {
      const userUpdated = await User.findByIdAndUpdate(
        req.params.id,
        {
          email: req.body.email,
          password: req.body.password,
          name: req.body.name,
          username: req.body.username
        },
        { new: true }
      );
  
      if (!userUpdated) return res.status(404).json({ missatge: 'Usuari no trobada' });
  
      res.json(userUpdated);
    } catch (error) {
      res.status(500).json({ missatge: 'Error al actualitzar el usuari', error });
    }
});

router.delete('/:id', async (req, res) => {
    try {
      const userDelete = await User.findByIdAndDelete(req.params.id);
      if (!userDelete) return res.status(404).json({ missatge: 'Usuari no trobada' });
      res.json({ missatge: 'Usuari eliminada' });
    } catch (error) {
      res.status(500).json({ missatge: 'Error al eliminar el usuari', error });
    }
});

module.exports = router;