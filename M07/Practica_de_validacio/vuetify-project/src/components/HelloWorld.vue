<template>
    <v-list v-for="index in dades" :key="index">
        <h2>{{ index.Criteri }}</h2>
        <v-list-item v-for="index in index.Preguntes" :key="index">
            <p>{{ index }}</p>
            <v-radio-group>
                <v-radio label="Si" value="Si"></v-radio>
                <v-radio label="No" value="No"></v-radio>
            </v-radio-group>
        </v-list-item>
    </v-list>
</template>

<script setup>
import { ref } from 'vue';
import validacio from '@/assets/validacio.json'

const dades = ref(validacio.LlistaVerificacióUsabilitat);
</script>