<template>
  <v-app>
    <v-container>
    <!-- Barra de Navegación -->
      <v-app-bar app color="primary" dark>
        <v-toolbar-title>Mi Primera App con Vuetify</v-toolbar-title>
      </v-app-bar>
      <br><br><br>
        <!-- Alerta -->
      <v-alert v-if="mostrarAlerta" type="success">
        ¡Hola! Has presionado el botón.
      </v-alert>

      <!-- Botón -->
      <v-btn color="purple" @click="toggleAlerta">
        Presióname
      </v-btn>

      <v-btn color="red" @click="toggleTarjeta">
        Ocultar/Mostrar
      </v-btn>

      <!-- Tarjeta con imagen -->
      <v-card v-if="mostrarTarjeta" class="mt-5" max-width="400">
        <v-img src="https://source.unsplash.com/random/400x200" height="200px"></v-img>
        <v-card-title> Tarjeta de Ejemplo </v-card-title>
        <v-card-text>Esta es una tarjeta simple con Vuetify.</v-card-text>
      </v-card>
    </v-container>
  </v-app>
</template>

<script setup>
import { ref } from 'vue';

const mostrarAlerta = ref(false);
const mostrarTarjeta = ref(true);

const toggleAlerta = () => {
  mostrarAlerta.value = !mostrarAlerta.value;
};

const toggleTarjeta = () => {
  mostrarTarjeta.value = !mostrarTarjeta.value;
};
</script>
