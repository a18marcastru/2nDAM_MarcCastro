package com.example.process.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.process.ui.screens.ProcessScreen

@Composable
fun ProcessApp() {
    val viewModel: ProcessViewModel = viewModel()
    val apis = viewModel.api.value
    val mongo = viewModel.mongo.value
    val mysql = viewModel.mysql.value
    if (apis != null && mongo != null && mysql != null) {
        ProcessScreen(apis, mongo, mysql, viewModel)
    }
}