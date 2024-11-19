<template>
    <v-container>
        <v-row>
            <v-col v-for="index in dades" :key="index">
                <v-card :title="index.Teòric">
                    <v-card-text>
                        {{ index.Contribució }}
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
<script setup>
    import { ref } from "vue";
    import historia from "@/assets/historia.json"

    const dades = ref(historia.IntroduccióHistòrica);
</script>