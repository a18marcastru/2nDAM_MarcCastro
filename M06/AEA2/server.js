const express = require('express');
const cors = require('cors');
const fileUpload = require('express-fileupload');
const app = express();
const fs = require('fs');

// Middleware
app.use(cors({ origin: '*' }));
app.use(express.json());
app.use(fileUpload());

// Base de dades simulada (en memòria)
let products = [];
let currentId = 1;

const uploadDir = './upload';
if(!fs.existsSync(uploadDir)) fs.mkdirSync(uploadDir);

// Endpoints CRUD
app.get('/products', (req, res) => {
    res.json(products);
});

app.get('/products/:id', (req, res) => {
    const product = products.find(p => p.id === parseInt(req.params.id));
    if (product) res.json(product);
    else res.status(404).send('Producte no trobat');
});

app.delete('/products/:id', (req, res) => {
    products = products.filter(p => p.id !== parseInt(req.params.id));
    res.send('Producte eliminat');
});

app.put('/products/:id', (req, res) => {
    const { id, name, description, price, image } = req.body;
    const index = products.findIndex(p => p.id === parseInt(req.params.id));
    if (index === -1) return res.status(404).send('Producte no trobat');

    products[index] = { id: parseInt(id), name, description, price, image };
    res.send('Producte actualitzat');
});

app.post('/products', (req, res) => {
    const { name, description, price } = req.body;
    const imageFile = req.files.image;

    const uploadPath = `${uploadDir}/${imageFile.name}`;

    imageFile.mv(uploadPath, (err) => {
        if(err) return res.status(500).send(err);
    });

    const newProduct = {
        id: currentId++,
        name,
        description,
        price: parseFloat(price),
        imageUrl: uploadPath
    };

    products.push(newProduct);
    res.status(201).json(newProduct);
});

// Arrancar el servidor
const PORT = 7000; // Port del servei
app.listen(PORT, () => {
    console.log(`Servidor escoltant al port ${PORT}`);
});