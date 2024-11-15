package com.example.process.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.process.ui.screens.ProcessScreen

@Composable
fun ProcessApp(viewModel: ProcessViewModel = viewModel()) {
    val uiState by viewModel.uiSate.collectAsState()
    val procesos = uiState.procesos
    ProcessScreen(procesos, viewModel)
}