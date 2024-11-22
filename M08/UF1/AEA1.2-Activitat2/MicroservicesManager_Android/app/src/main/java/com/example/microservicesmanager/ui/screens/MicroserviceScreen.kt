package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.microservicesmanager.model.Log
import com.example.microservicesmanager.model.Microservice
import com.example.microservicesmanager.ui.MicroserviceViewModel

@Composable
fun MicroserviceScreen(microservices: List<Microservice>, logs: List<Log>, viewModel: MicroserviceViewModel) {
    var showLogs by remember { mutableStateOf(false) }
    var showLogsError by remember { mutableStateOf(false) }
    LazyColumn(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        item {
            Text(text = "List of Microservice", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(23.dp))
        }
        items(microservices) { index ->
            Row {
                Text(text = index.title)
                Button(
                    onClick = { viewModel.functionMicroservice(index.title, index.activated) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (index.activated == 0) Color.Green else Color.Red
                    )
                ) {
                    Text(text = index.button1)
                }
                Button(
                    onClick = {
                        viewModel.callLogs(index.title)
                        showLogs = true
                        showLogsError = false
                    }
                ) {
                    Text(text = index.button2)
                }
                Button(
                    onClick = {
                        viewModel.callLogsError(index.title)
                        showLogsError = true
                        showLogs = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(text = index.button3)
                }
            }
        }
        if(showLogs) {
            item {
                Spacer(modifier = Modifier.height(23.dp))
                Text(text = "Logs")
            }
            items(logs) { index ->
                Text(text = index.log)
            }
        }
        else if(showLogsError) {
            item {
                Spacer(modifier = Modifier.height(23.dp))
                Text(text = "LogsError")
            }
            items(logs) { index ->
                Text(text = index.log)
            }
        }
    }
}