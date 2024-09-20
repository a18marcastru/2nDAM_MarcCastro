const express = require('express');
const app = express();
const fs = require('fs');
const path = require('path');
const port = 3000;

const obj = '{"employees":[' +
'{"firstName":"John","lastName":"Doe" },' +
'{"firstName":"Anna","lastName":"Smith" },' +
'{"firstName":"Peter","lastName":"Jones" }]}';

app.get('/', (req, res) => {
  //const newObj = aleatorio(obj);
  const pathDir = __dirname + "\\Jocs";
  
  const fechaActual = new Date();
  const dia = fechaActual.getDate();
  const mes = fechaActual.getMonth() + 1;
  const year = fechaActual.getFullYear(); 
  const pathDirective = path.join(pathDir, `${dia}-${mes}-${year}`);

  if(!fs.existsSync(pathDirective)) {
    fs.mkdir(pathDirective, (err) => {
      if(err) return res.send("Error");
      else return res.send("Creada");
    });
  }
  else res.send("Ya existe");
  
  
  

  // fs.writeFile(pathFile, obj, (err) => {
  //   if(err) return res.status(500).json({mesaje: 'Error al crear archivo'})
  //   res.status(200).json({mensaje: 'Archivo creado'})
  // });
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});

// aleatorio(obj) {
  
// }