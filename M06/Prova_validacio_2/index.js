import { connectarBD, inserirAlumnes, obtenirAlumnes, tancarBD } from './db.js';

const URI = 'mongodb+srv://a18marcastru:mongodb@cluster24-25.38noo.mongodb.net/';
const DB_NAME = 'alumnesDB';
const alumnesPerInserir = [

  { nom: 'Joan', edat: 18, curs: '2n Batx' },

  { nom: 'Maria', edat: 17, curs: '1r Batx' },

  { nom: 'Pau', edat: 19, curs: 'CFGM DAW' }

];

await connectarBD(URI, DB_NAME);

const numInserits = await inserirAlumnes(alumnesPerInserir);
console.log(`S'han inserit ${numInserits} alumnes!`);

const alumnes = await obtenirAlumnes();
console.log('Llistat d’alumnes a la BD:');
console.log(alumnes);

await tancarBD();
console.log('Connexió tancada.');