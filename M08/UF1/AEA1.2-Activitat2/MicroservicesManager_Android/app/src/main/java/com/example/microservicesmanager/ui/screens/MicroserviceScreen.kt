package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.microservicesmanager.data.model.Log
import com.example.microservicesmanager.data.model.Microservice
import com.example.microservicesmanager.ui.MicroserviceApp
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MicroserviceScreen(
    microservices: List<Microservice>,
    logs: List<Log>,
    viewModel: MicroserviceViewModel,
    navController: NavController
) {
    var showLogs by remember { mutableStateOf(false) }
    var showLogsError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { navController.navigate(MicroserviceApp.Profiles.name) }) {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Go to Profile"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Green
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            item {
                Text(text = "List of Microservice", style = MaterialTheme.typography.titleLarge)
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
            if(showLogs || showLogsError) {
                item {
                    Spacer(modifier = Modifier.height(23.dp))
                    Text(text = if(showLogs) "Logs" else "LogsError")
                }
                items(logs) { index ->
                    Text(text = index.log)
                }
            }
        }
    }
}