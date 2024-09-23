const express = require('express')
const fs = require('fs')
const path = require('path')
const app = express()
const port = 3000

app.use(express.json())

let numQ = [];

app.get('/', (req, res) => {
    res.send('Hello')
})

app.post('/getPreguntes', (req, res) => {
    const {num} = req.query;
    const filePath = path.join(__dirname, 'Quiz.json');

    fs.readFile(filePath, 'utf-8', (err, data) => {
        if(err) res.send('Error')
        else {

            const newData = JSON.parse(data)

            res.json(random(num, newData))
        }
    })
})

function random(num, jsonData) {

    const questions = jsonData.questions;

    for(let i = 0;i < num;i++) {
        numQ[i] = questions[Math.floor(Math.random() * questions.length)];
    }

    const newJson = numQ.map(q => ({
        question: q.question,
        answers: q.answers
    }));

    return newJson;
}

app.post('/finalista', (req, res) => {
    const results = [];
    for(let i = 0;i < req.body.length;i++) results.push(req[i].body);
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
});