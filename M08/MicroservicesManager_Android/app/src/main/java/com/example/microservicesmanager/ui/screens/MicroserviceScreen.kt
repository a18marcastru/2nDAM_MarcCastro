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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.microservicesmanager.model.Microservice
import com.example.microservicesmanager.ui.MicroserviceViewModel

@Composable
fun MicroserviceScreen(microservices: List<Microservice>, viewModel: MicroserviceViewModel) {
    var showLogs = 0;
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
                Button(onClick = { viewModel.callLogs(index.title) }) {
                    Text(text = index.button2)
                    showLogs = 1
                }
                Button(onClick = {  }) {
                    Text(text = index.button3)
                }
            }
        }
        item {
            if(showLogs == 1) {
                Box() {

                }
            }
        }
    }
}