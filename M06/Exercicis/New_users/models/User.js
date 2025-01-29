const mongoose = require('mongoose');

const UserSchema = new mongoose.Schema({
    email: { type: String, required: true },
    password: { type: String, required: true },
    name: { type: String, required: false },
    username: { type: String, required: false }
}, {
    timeStamp: true
});

module.exports = mongoose.model('User', UserSchema);