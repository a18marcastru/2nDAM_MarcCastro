const express = require('express')
const app = express()
const port = 3000

app.use(express.json());

const objUsers = { "users": [
    {"user": "Marc", "password": "mar13", "roles": ["admin", "edit"]},
    {"user": "Alex", "password": "ale33", "roles": ["client"]},
    {"user": "Juli", "password": "jul45", "roles": ["edit"]}
]};

app.get('/auth/:user/:password', (req, res) => {
    let objUser = {user: req.params.user, password: req.params.password};

    // Prueba 1

    let isAuth = false;
    let num = 0;

    for(let i = 0;i < objUsers.users.length;i++) {
        if(objUsers.users[i].user == objUser.user && objUsers.users[i].password == objUser.password) {
            isAuth = true;
            num = i;
        }
    }
    
    if(isAuth) res.json({isAuth: true, roles: objUsers.users[num].roles});
    else res.json({"isAuth": false, "roles": []});

    // Prueba 2 

    // for(let i = 0;i < objUsers.users.length;i++) {
    //     if(objUsers.users[i].user == obj.user && objUsers.users[i].password == obj.password) {
    //         res.json({"isAuth": true, "roles": objUsers.users[num].roles});
    //     }
    //     console.log(i)
    // }

    // res.json({isAuth: false, roles: []});
    
});

app.post('/auth',(req, res) => {
    
    const {user, password} = req.body;

    let isAuth = false;
    let num = 0;

    for(let i = 0;i < objUsers.users.length;i++) {
        if(objUsers.users[i].user == user && objUsers.users[i].password == password) {
            isAuth = true;
            num = i;
        }
    }
    
    if(isAuth) res.json({isAuth: true, roles: objUsers.users[num].roles});
    else res.json({isAuth: false, roles: []});
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}`);
});