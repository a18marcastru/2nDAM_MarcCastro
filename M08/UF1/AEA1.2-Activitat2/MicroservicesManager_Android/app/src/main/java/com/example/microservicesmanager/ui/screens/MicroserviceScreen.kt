package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.microservicesmanager.data.model.Log
import com.example.microservicesmanager.data.model.Microservice
import com.example.microservicesmanager.data.model.Profile
import com.example.microservicesmanager.data.model.Profiles
import com.example.microservicesmanager.ui.MicroserviceApp
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MicroserviceScreen(
    microservices: List<Microservice>,
    logs: List<Log>,
    viewModel: MicroserviceViewModel,
    navController: NavController,
    uiStateProfiles: Profiles
) {
    var expanded by remember { mutableStateOf(false) }
    var showLogs by remember { mutableStateOf(false) }
    var showLogsError by remember { mutableStateOf(false) }
    var selectedProfile by remember { mutableStateOf<Profile?>(null) }
    var color by remember { mutableStateOf(Color.White) }

    LaunchedEffect(uiStateProfiles.profiles) {
        if (uiStateProfiles.profiles.isNotEmpty()) {
            selectedProfile = uiStateProfiles.profiles.find { it.predetermined } ?: uiStateProfiles.profiles.firstOrNull()
            selectedProfile?.let {
                color = Color(android.graphics.Color.parseColor(it.color))
            }
        }
    }

    android.util.Log.d("Profile", selectedProfile.toString())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(6.dp))
                                .border(1.dp, Color.Black, RoundedCornerShape(6.dp))
                                .clickable { expanded = true }
                                .padding(horizontal = 6.dp, vertical = 4.dp)
                                .height(32.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 6.dp)
                            ) {
                                Text(
                                    text = selectedProfile?.label ?: "Perfil",
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(end = 4.dp)
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Expandir",
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                uiStateProfiles.profiles.forEach { profile ->
                                    DropdownMenuItem(
                                        text = { Text(text = profile.label, fontSize = 14.sp) },
                                        onClick = {
                                            selectedProfile = profile
                                            expanded = false
                                            color = Color(android.graphics.Color.parseColor(selectedProfile?.color))
                                        }
                                    )
                                }
                            }
                        }

                        Button(onClick = { navController.navigate(MicroserviceApp.ListProfiles.name) }) {
                            Text("Profiles")
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = color
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