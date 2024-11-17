package com.example.musics.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        items(musics) { index ->
            Row {
                TextField(
                    value = index.name,
                    onValueChange = {index.name },
                    modifier = Modifier.weight(1f)
                )
                Button(onClick = { viewModel.updateMusic(index.id, index.name) }) {
                    Text(text = "Editar")
                }
                Button(onClick = { viewModel.deleteMusic(index.id)}) {
                    Text(text = "Eliminar")
                }
            }
        }
    }
}