// models/index.js
import { Sequelize } from 'sequelize';
import dotenv from 'dotenv';

dotenv.config();

// Creem la instància de Sequelize
const sequelize = new Sequelize(
  process.env.MYSQL_DATABASE,
  process.env.MYSQL_USER,
  process.env.MYSQL_PASSWORD,
  {
    host: process.env.MYSQL_HOST || 'mysql',
    dialect: 'mysql',
    logging: false,
  }
);

// Importem les definicions dels models (exportades com a funcions)
import defineCategoria from './categoria.js';
import defineMotocicleta from './motocicleta.js';
import defineComentari from './comentari.js';

// Inicialitzem els models passant la instància de Sequelize
const Categoria = defineCategoria(sequelize);
const Motocicleta = defineMotocicleta(sequelize);
const Comentari = defineComentari(sequelize);

// Definim les associacions
// Una Categoria pot tenir moltes Motocicletes (1-N)
Categoria.hasMany(Motocicleta, { foreignKey: 'categoriaId', onDelete: 'CASCADE' });
Motocicleta.belongsTo(Categoria, { foreignKey: 'categoriaId' });

Motocicleta.hasMany(Comentari, { foreignKey: 'motoId', onDelete: 'CASCADE' });
Comentari.belongsTo(Motocicleta, { foreignKey: 'motoId' });

// Exportem la instància de Sequelize i els models
export { sequelize, Categoria, Motocicleta, Comentari };
export default sequelize;
