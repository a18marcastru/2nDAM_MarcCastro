import { MongoClient } from "mongodb";

var database;
var client;

export async function connectarBD(URI, DB_NAME) {
    const mongoUrl = URI;
    client = new MongoClient(mongoUrl);

    try {
        database = client.db(DB_NAME);
        console.log('Connexió establerta correctament!');
    } catch (err) {
        console.log("Error de conexió", err);
    }
}

export async function inserirAlumnes(alumnesPerInserir) {
    try {
        const result = await database.collection('alumnes').insertMany(alumnesPerInserir);
        return result.insertedCount;
    } catch(err) {
        console.log(err);
    }
}

export async function obtenirAlumnes() {
    try {
        const result = await database.collection('alumnes').find().toArray();
        return result;
    } catch (err) {
        console.log(err);
    }
}

export async function tancarBD() {
    await client.close()
}