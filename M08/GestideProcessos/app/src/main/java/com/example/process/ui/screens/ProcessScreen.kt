package com.example.process.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.process.ui.ProcessViewModel
import com.example.process.ui.model.Procesos

@Composable
fun ProcessScreen(procesos: List<Procesos>, viewModel: ProcessViewModel) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        items(procesos) { index ->
            Row {
                Text(text = index.title)
                Button(onClick = { viewModel.Start(index.title) }) {
                    Text(text = index.button1)
                }
                Button(onClick = {  }) {
                    Text(text = index.button2)
                }
                Button(onClick = {  }) {
                    Text(text = index.button3)
                }
            }
        }
    }
}