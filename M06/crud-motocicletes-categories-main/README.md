# 🏍️ CRUD Motocicletes + Categories 🚀

Benvingut al projecte **CRUD Motocicletes + Categories**!  
Aquesta aplicació web et permet gestionar motocicletes i categories, on cada motocicleta pot estar associada a una categoria. El projecte està desenvolupat amb **Node.js** i **Express**, utilitza **Sequelize** per treballar amb la base de dades **MySQL** i genera les vistes amb **EJS**. A més, tot està empaquetat en contenidors Docker per facilitar-ne el desplegament, i incorpora eines com **Adminer** per gestionar la BD i **nodemon** per a un desenvolupament més àgil.

---

## 📂 Estructura del Projecte

La nova organització de carpetes és la següent:

```
crud-motocicletes/
├── models/
│   ├── index.js           # Inicialitza Sequelize, importa els models i defineix les associacions.
│   ├── categoria.js       # Funció que defineix el model Categoria.
│   └── motocicleta.js     # Funció que defineix el model Motocicleta.
├── routes/
│   ├── motocicletes.js    # Rutes per gestionar el CRUD de les motocicletes.
│   └── categories.js      # Rutes per gestionar el CRUD de les categories.
├── views/
│   ├── partials/          # Partials reutilitzables (header i footer).
│   │   ├── header.ejs     # Capçalera comuna amb Bootstrap.
│   │   └── footer.ejs     # Peu de pàgina comú amb scripts de Bootstrap.
│   ├── portada.ejs        # Pàgina de benvinguda.
│   ├── motocicletes/      # Vistes relacionades amb les motocicletes.
│   │   ├── index.ejs      # Llistat de motocicletes.
│   │   ├── nou.ejs        # Formulari per crear una nova motocicleta.
│   │   ├── editar.ejs     # Formulari per editar una motocicleta.
│   │   └── mostrar.ejs    # Detalls d'una motocicleta.
│   └── categories/        # Vistes relacionades amb les categories.
│       ├── index.ejs      # Llistat de categories (i les motocicletes associades).
│       ├── nou.ejs        # Formulari per crear una nova categoria.
│       └── editar.ejs     # Formulari per editar una categoria.
├── public/
│   └── css/
│       └── style.css      # Estils personalitzats.
├── app.js                 # Configuració del servidor Express.
├── package.json           # Dependències i scripts.
├── .env                   # Variables d'entorn (configuració de la BD, port, etc.).
├── Dockerfile             # Instruccions per construir la imatge Docker.
└── docker-compose.yml     # Configuració dels contenidors (Node.js, MySQL, Adminer).
```

---

## 🛠️ Tecnologies i Eines

- **Node.js + Express**  
  El backend gestiona les rutes, la lògica de negoci i la interacció amb la base de dades.

- **Sequelize (ORM)**  
  Permet treballar amb MySQL mitjançant models de JavaScript, evitant escriure consultes SQL complexes i mantenint el codi net i entenedor.

- **EJS**  
  Motor de plantilles que genera HTML dinàmic. Utilitzem partials per reutilitzar elements comuns com el header i el footer.

- **Bootstrap**  
  S'ha integrat Bootstrap a través dels partials per dotar les vistes d'un disseny responsive i modern sense haver de programar tot des de zero.

- **Docker i Docker Compose**  
  El projecte s'executa en contenidors: un per a **MySQL** (amb persistència de dades), un per a l'aplicació Node.js i un per a **Adminer**, una eina gràfica per gestionar la base de dades.

- **Nodemon**  
  Facilita el desenvolupament reiniciant automàticament el servidor quan es detecten canvis en el codi.

- **.env**  
  Fitxer que conté la configuració sensible (credencials de la BD, port, etc.) separada del codi font.  
  Exemple:
  ```env
  PORT=3000
  MYSQL_HOST=mysql
  MYSQL_DATABASE=crud_motocicletes
  MYSQL_USER=usuari
  MYSQL_PASSWORD=usuari_password
  ```

---

## 💪 Com executar el projecte

1. **Arrencar els contenidors:**
   ```bash
   docker-compose up --build
   ```

2. **Accedir a l'aplicació:**
   - Pàgina de benvinguda: [http://localhost:3000](http://localhost:3000)
   - Motocicletes: [http://localhost:3000/motocicletes](http://localhost:3000/motocicletes)
   - Categories: [http://localhost:3000/categories](http://localhost:3000/categories)
   - Adminer: [http://localhost:8080](http://localhost:8080)

3. **Aturar els contenidors:**
   - Aturar sense eliminar:
     ```bash
     docker-compose stop
     ```
   - Aturar i eliminar contenidors (i volums si cal):
     ```bash
     docker-compose down -v
     ```

---

## 🔍 Conclusió

Amb aquesta estructura:
- Totes les vistes estan organitzades de manera coherent (les de motocicletes i categories en subcarpetes dins de `views/`).
- S'utilitzen partials per centralitzar el header i footer amb Bootstrap, fent el codi més net i fàcil de mantenir.
- El backend està modularitzat en routers separats, facilitant futures ampliacions.
- El projecte utilitza Docker per garantir un entorn de desenvolupament consistent, amb Adminer per gestionar la base de dades i nodemon per a una experiència de desenvolupament ràpida.
