<script setup>
import { ref, reactive } from 'vue';
const welcome = ref('Hello World');
const counter = reactive({count: 0});
const llista = reactive(
  {id: 1, pregunta: "bla", respostas:[1,2,3]},
  {id: 2, pregunta: "bli", respostas:[1,2,3]},  
  {id: 3, pregunta: "blo", respostas:[1,2,3]});

function incrementar() {
  counter.count++;
}

function descrementar() {
  counter.count--;
}
</script>

<template>
  <h1>{{ welcome }}</h1>
  <br>
  <h2 v-if="counter.count > 5">Es grande {{ counter.count }}</h2>
  <h2 v-else>Es pequeño {{ counter.count }}</h2>
  <br>
  <button @click="incrementar">Incrementar</button>
  <button @click="descrementar">Descrementar</button>
  <ul>
    <li v-for="lli in llista" :key="lli.id">{{ llista.pregunta }} {{ llista.respostas[0] }} {{ llista.respostas[1] }} {{ llista.respostas[2] }}</li>
  </ul>
</template>

<style scoped>
h1 {
  color: red;
}
</style>
