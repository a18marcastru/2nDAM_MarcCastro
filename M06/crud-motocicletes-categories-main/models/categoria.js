// models/categoria.js
import { DataTypes } from 'sequelize';

export default (sequelize) => {
  return sequelize.define('Categoria', {
    id: {
      type: DataTypes.INTEGER,
      autoIncrement: true,
      primaryKey: true,
    },
    nombre: {
      type: DataTypes.STRING,
      allowNull: false,
    }
  }, {
    tableName: 'categories',
    timestamps: false,
  });
};
