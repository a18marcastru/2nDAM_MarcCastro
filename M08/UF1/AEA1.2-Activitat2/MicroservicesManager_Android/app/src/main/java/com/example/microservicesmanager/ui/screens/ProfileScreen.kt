package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController, viewModel: MicroserviceViewModel) {
    var label by remember { mutableStateOf("") }
    var host by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("0") }
    var checked by remember { mutableStateOf(false) }
    var selectedColor by remember { mutableStateOf(Color.Black) }
    var expanded by remember { mutableStateOf(false) }

    val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Black)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "New Profile") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Green
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Text("New Profile", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(23.dp))

                TextField(
                    value = label,
                    onValueChange = { label = it },
                    label = { Text(text = "Label") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = host,
                    onValueChange = { host = it },
                    label = { Text(text = "Host") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = port,
                    onValueChange = { port = it },
                    label = { Text(text = "Port") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Selector de color
                Text(text = "Seleccionar color:")
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(selectedColor, shape = CircleShape)
                        .clickable { expanded = true }
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    colors.forEach { color ->
                        DropdownMenuItem(
                            text = { Box(Modifier.size(24.dp).background(color, shape = CircleShape)) },
                            onClick = {
                                selectedColor = color
                                expanded = false
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Predetermined")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    val colorHex = "#%06X".format(0xFFFFFF and selectedColor.toArgb()) // Convertir color a HEX
                    viewModel.addProfile(label, host, port, colorHex, checked)

                    // Resetear valores
                    label = ""
                    host = ""
                    port = ""
                    checked = false
                    selectedColor = Color.Black
                }) {
                    Text(text = "Añadir")
                }
            }
        }
    }
}
