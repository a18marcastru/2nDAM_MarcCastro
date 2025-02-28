// models/motocicleta.js
import { DataTypes } from 'sequelize';

export default (sequelize) => {
  return sequelize.define('Motocicleta', {
    id: {
      type: DataTypes.INTEGER,
      autoIncrement: true,
      primaryKey: true,
    },
    marca: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    model: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    descripcio: {
      type: DataTypes.TEXT,
      allowNull: true,
    },
    potencia: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    // Clau forana per relacionar amb Categoria
    categoriaId: {
      type: DataTypes.INTEGER,
      allowNull: true,
    }
  }, {
    tableName: 'motocicletes',
    timestamps: false,
  });
};
