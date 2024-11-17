package com.example.musics.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.musics.ui.screens.MusicsScreen

@Composable
fun MusicsApp(viewModel: MusicsViewModel = viewModel()) {
    val uiState by viewModel.uiSate.collectAsState()
    val musics = uiState.musics
    MusicsScreen(musics, viewModel)
}