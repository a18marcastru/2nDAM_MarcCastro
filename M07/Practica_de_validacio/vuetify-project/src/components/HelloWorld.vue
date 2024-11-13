<template>
    <div v-for="index in dades" :key="index">
        <h2>{{ index.Criteri }}</h2>
        <div v-for="index in index.Preguntes" :key="index">
            <p>{{ index }}</p>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import validacio from '@/assets/validacio.json'

const dades = ref(validacio.LlistaVerificacióUsabilitat);
</script>