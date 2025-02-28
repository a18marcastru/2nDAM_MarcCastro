# Utilitzem una imatge oficial de Node.js
FROM node:18-alpine

# Establim el directori de treball dins del contenidor
WORKDIR /app

# Copiem els fitxers de package.json i package-lock.json (si existeixen)
COPY package*.json ./

# Instal·lem les dependències del projecte
RUN npm install

# Copiem la resta del codi
COPY . .

# Exposem el port de l'aplicació
EXPOSE 3000

# Instal·lem nodemon com a dependència global per al desenvolupament
RUN npm install -g nodemon

# Comanda per iniciar l'aplicació en mode desenvolupament
CMD ["npm", "run", "dev"]
