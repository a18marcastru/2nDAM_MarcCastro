package com.example.microservicesmanager.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.microservicesmanager.data.model.Profile
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilesScreen(profiles: List<Profile>, navController: NavController, viewModel: MicroserviceViewModel) {
    var label by remember { mutableStateOf("") }
//    var color by remember { mutableStateOf("") }
    var host by remember { mutableStateOf("0") }
    var port by remember { mutableStateOf("0") }
    var checked by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Tornar")
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
                Text(text = "Profiles", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(23.dp))

                TextField(
                    value = label,
                    onValueChange = { label = it },
                    label = { Text(text = "label") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                TextField(
                    value = host,
                    onValueChange = { host = it },
                    label = { Text(text = "host") },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                TextField(
                    value = port,
                    onValueChange = { port = it },
                    label = { Text(text = "port") },
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(23.dp))
                Text(text = "Predetermined")
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it }
                )
                Button(onClick = {
                    viewModel.addProfile(label, host, port, checked)
                    label = ""
                    host = ""
                    port = ""
                    checked = false
                }) {
                    Text(text = "Añadir")
                }
            }
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween // Espacio entre columnas
                ) {
                    Text(text = "Label", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Color", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Host", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Port", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Predetermined", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }

            // Iterar sobre los perfiles
            items(profiles) { profile ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween // Espacio entre columnas
                ) {
                    Text(text = profile.label, style = MaterialTheme.typography.bodyMedium)

                    // Mostrar un indicador de color con color predeterminado si está vacío
                    val parsedColor = try {
                        if (profile.color.isNotBlank()) {
                            Color(android.graphics.Color.parseColor(profile.color))
                        } else {
                            Color.Gray // Color predeterminado si el valor está vacío
                        }
                    } catch (e: IllegalArgumentException) {
                        Color.Gray // Color predeterminado si el formato es inválido
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp) // Tamaño del indicador de color
                            .background(color = parsedColor, shape = CircleShape) // Forma circular
                    )

                    Text(text = profile.host.toString(), style = MaterialTheme.typography.bodyMedium)
                    Text(text = profile.port.toString(), style = MaterialTheme.typography.bodyMedium)
                    Text(
                        text = if (profile.predetermined) "Yes" else "No",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    IconButton(onClick = { viewModel.deleteProfile(profile.id)}) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete profile",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}