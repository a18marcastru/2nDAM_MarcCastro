const mongoose = require('mongoose');

const BookSchema = new mongoose.Schema({
    title: { type: String, required: true },
    autor: { type: String, required: true },
    year: { type: String, required: false },
    genere: { type: String, required: false }
}, {
    timeStamp: true
});

module.exports = mongoose.model('Book', BookSchema, 'book');