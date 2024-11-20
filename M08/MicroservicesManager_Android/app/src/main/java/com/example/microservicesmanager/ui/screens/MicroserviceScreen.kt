package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.microservicesmanager.model.Log
import com.example.microservicesmanager.model.Microservice
import com.example.microservicesmanager.ui.MicroserviceViewModel

@Composable
fun MicroserviceScreen(microservices: List<Microservice>, logs: List<Log>, viewModel: MicroserviceViewModel) {
    var showLogs by remember { mutableStateOf(false) }
    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        items(microservices) { index ->
            Row {
                Text(text = index.title)
                Button(onClick = { viewModel.functionMicroservice(index.title, index.activated) }) {
                    Text(text = index.button1)
                }
                Button(onClick = {
                    viewModel.callLogs(index.title)
                    showLogs = true
                }) {
                    Text(text = index.button2)
                }
                Button(onClick = {  }) {
                    Text(text = index.button3)
                }
            }
        }
        if(showLogs) {
            item {
                Text(text = "Logs")
            }
            items(logs) { index ->
                Text(text = index.log)
            }
        }
    }
}