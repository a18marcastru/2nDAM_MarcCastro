const express = require('express')
const { v4: uuidv4 } = require('uuid');
const fs = require('fs')
const path = require('path')
const app = express()
const port = 3000

app.use(express.json())

let quizJson
let mySession = []
let jsonOriginal;

app.get('/', (req, res) => {
    
})

app.post('/getPreguntes', (req, res) => {
    const {num} = req.query;
    const filePath = path.join(__dirname, 'Quiz.json');

    fs.readFile(filePath, 'utf-8', (err, data) => {
        if(err) res.send('Error')
        else {

            const newData = JSON.parse(data)

            quizJson = random(num, newData)

            getMySessionId(req.query["sessionId"], jsonOriginal);

            res.json(quizJson)
        }
    })
})

function random(num, jsonData) {

    const questions = jsonData.questions;
    let numQ = [];

    for(let i = 0;i < num;i++) {
        numQ[i] = questions[Math.floor(Math.random() * questions.length)];
    }

    jsonOriginal = numQ.map(q => ({
        question: q.question,
        answers: q.answers,
        correctIndex: q.correctIndex
    }));

    const newJson = numQ.map(q => ({
        question: q.question,
        answers: q.answers
    }));

    return newJson;
}

function getMySessionId(sessionId, jsonOriginal){
    if(!sessionId){
        sessionId = uuidv4();
        let obj = {};
        obj.sessionId = sessionId;
        obj.data = jsonOriginal;
        mySession[sessionId] = obj;
    }
}

app.post('/finalista', (req, res) => {
    const { results } = req.body

    const sessionKey = Object.keys(mySession)[0];

    const sessionData = mySession[sessionKey].data;

    console.log(sessionData);

    let count = 0, i = 0;

    sessionData.forEach(item => {
        if(item.correctIndex === results[i]) {
            count++;
        }
        i++;
    });

    res.json({"success": count,"total": results.length});

    // const resultsOriginal = mySession.data.correctIndex;

    // console.log(resultsOriginal)
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
});