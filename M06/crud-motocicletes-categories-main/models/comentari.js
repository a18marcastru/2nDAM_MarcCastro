// models/comentari.js
import { DataTypes } from 'sequelize';

export default (sequelize) => {
  return sequelize.define('Comentari', {
    id: {
      type: DataTypes.INTEGER,
      autoIncrement: true,
      primaryKey: true,
    },
    motoId: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    comentari: {
      type: DataTypes.STRING,
      allowNull: false
    },
    usuari: {
      type: DataTypes.STRING,
      allowNull: true
    },
    calendar: {
      type: DataTypes.DATE,
      allowNull: false
    }
  }, {
    tableName: 'comentaris',
    timestamps: false,
  });
};
