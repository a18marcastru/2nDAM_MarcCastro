<script setup>
import { ref } from 'vue';
import axios from 'axios';

const searchQuery = ref('');
const movies = ref([]);
const dialogVisible = ref(false);
const selectedMovie = ref('');

async function searchMovies() {
  const url = `http://www.omdbapi.com/?s=${searchQuery.value}&apikey=API`;

  try {
    const response = await axios.get(url);
    movies.value = response.data.Search;
  } catch (error) {
    console.error('Error buscant pel·lícules:', error);
  }
};

async function openMovieInfo (movie) {
  dialogVisible.value = true;
  const title = movie.Title

  const url = `http://www.omdbapi.com/?t=${title}&apikey=API`;

  try {
    const response = await axios.get(url);
    selectedMovie.value = response.data;
  } catch (error) {
    console.error('Error buscant la pel·lícula:', error);
  }
};

const closeMovieInfo = () => {
  dialogVisible.value = false;
  selectedMovie.value = '';
};
</script>

<template>
  <v-layout class="rounded rounded-md">
    <v-app-bar title="Cercador de Pel·lícules"></v-app-bar>

    <v-navigation-drawer>
      <v-list>
        <v-list-item title="Navigation drawer"></v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main class="d-flex align-center justify-center" style="min-height: 300px;">
      <v-container>
        <v-row>
          <v-col cols="9">
            <v-text-field
              v-model="searchQuery"
              label="Buscar pel·lícules"
            ></v-text-field>
          </v-col>

          <v-col cols="3">
            <v-btn @click="searchMovies">
              Buscar
            </v-btn>
          </v-col>
        </v-row>

        <v-row>
          <v-col
            v-for="index in movies"
            :key="index"
            cols="4"
          >
            <v-card>
              <v-img :src="index.Poster" height="300px"></v-img>
              <v-card-title>{{ index.Title }}</v-card-title>
              <v-card-subtitle>{{ index.Year }}</v-card-subtitle>
              <v-card-actions>
                <v-btn @click="openMovieInfo(index)" color="primary">
                  MÉS INFO
                </v-btn>
              </v-card-actions>
            </v-card>

            <v-dialog v-model="dialogVisible" max-width="800px">
              <v-card>
                <v-card-text>
                  <p>Genero: {{ selectedMovie.Genre }}</p>
                  <p>Director: {{ selectedMovie.Director }}</p>
                  <p>Actors: {{ selectedMovie.Actors }}</p>
                  <p>Plot: {{ selectedMovie.Plot }}</p>
                </v-card-text>
                <v-card-actions>
                  <v-btn @click="closeMovieInfo" color="secondary">Cerrar</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-layout>
</template>

<style scoped>
.v-card {
  margin-bottom: 20px;
}
</style>
