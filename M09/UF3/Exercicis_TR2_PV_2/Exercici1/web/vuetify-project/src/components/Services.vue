<template>
    <v-app>
        <v-container>
            <v-row>
                <v-btn @click="executeMysql">MySQL</v-btn>
                <v-btn @click="executeMongoDB">MongoDB</v-btn>
            </v-row>
        </v-container>
    </v-app>
</template>

<script setup>
import { funcionSocket } from "../services/socketManager.js";


const executeMysql = () => {
    funcionSocket("MySQL");
}
</script>