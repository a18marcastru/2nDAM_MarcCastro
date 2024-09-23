const express = require('express')
const fs = require('fs')
const path = require('path')
const app = express()
const port = 3000

app.use(express.json())

app.get('/', (req, res) => {
    res.send('Hello')
})

app.post('/getPreguntes', (req, res) => {
    console.log(req.body)
    const {num} = req.body;
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

    let numQ = [];
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

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`)
})