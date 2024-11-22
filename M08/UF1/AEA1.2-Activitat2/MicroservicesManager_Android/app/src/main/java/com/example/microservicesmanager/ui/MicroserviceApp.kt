package com.example.microservicesmanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.microservicesmanager.ui.screens.MicroserviceScreen

@Composable
fun MicroserviceApp(viewModel: MicroserviceViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val microservices = uiState.microservices
    val uiStateLogs by viewModel.uiStateLogs.collectAsState()
    val logs = uiStateLogs.logs
    MicroserviceScreen(microservices, logs, viewModel)
}