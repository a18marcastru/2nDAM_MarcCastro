import mongo from "mongoose";

export async function connectarBD(URI, DB_NAME) {
    try {
        const client = await mongo.connect(URI + DB_NAME);
        return client.db();
    } catch (err) {
        console.log("Error de conexió", err);
    }
}


export async function inserirAlumnes(URI, DB_NAME, alumnesPerInserir) {
    try {
        const database = await connectarBD(URI, DB_NAME);
        const result = await database.collection('alunmnes').insertMany(alumnesPerInserir)
        return result;
    } catch(err) {
        console.log(err);
    }
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
