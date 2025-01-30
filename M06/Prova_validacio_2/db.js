import mongo from "mongoose";

export async function connectarBD(URI, DB_NAME) {
    mongo.connect(URI + DB_NAME,  {
        useNewUrlParser: true,
        useUnifiedTopology: true,
    })
    .then(() => console.log('Connexió establerta correctament!'))
    .catch((err) => console.error('Error al connectar a MongoDB', err));
}


export async function inserirAlumnes(URI, DB_NAME, alumnesPerInserir) {

}

/*
export async function obtenirAlumnes() {
    return await db.collection('alumnesDB').find();
}
*/

export async function tancarBD() {
    mongo.connection.close()
    .then(() => console.log('Conexión cerrada'))
    .catch(err => console.error(err));
}
