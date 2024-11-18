package com.example.musics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.musics.model.Music
import com.example.musics.ui.MusicsViewModel

@Composable
fun MusicsScreen(musics: List<Music>, viewModel: MusicsViewModel) {
    var newMusic by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            TextField(
                value = newMusic,
                onValueChange = { newMusic = it },
                label = { Text(text = "Nueva musica") }
            )
            Button(onClick = { viewModel.addMusic(newMusic) }
            ) {
                Text(text = "Añadir musica")
            }
        }
        items(musics) { music ->
            var editedName by remember { mutableStateOf(music.name) }
            Row() {
                Text(text = music.name, modifier = Modifier.padding(20.dp))
                Button(onClick = { viewModel.updateMusic(music.id, editedName) }) {
                    Text(text = "Editar")
                }
                Button(onClick = { viewModel.deleteMusic(music.id)}) {
                    Text(text = "Eliminar")
                }
            }
        }
    }
}