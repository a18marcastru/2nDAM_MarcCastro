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
  const nombreArchivo = "archivo.json";

  const pathDirective = path.join(__dirname, 'JSON');
  const pathFile = path.join(__dirname, nombreArchivo);
  
  fs.mkdir

  fs.writeFile(pathFile, obj, (err) => {
    if(err) return res.status(500).json({mesaje: 'Error al crear archivo'})
    res.status(200).json({mensaje: 'Archivo creado'})
  });
});

app.listen(port, () => {
  console.log(`Example app listening on port ${port}`);
});

// aleatorio(obj) {
  
// }