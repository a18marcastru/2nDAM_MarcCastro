package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import com.example.microservicesmanager.data.model.Profile
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController,
    viewModel: MicroserviceViewModel,
    profile: Profile
) {

    var label by remember { mutableStateOf(profile.label) }
    var color by remember {  mutableStateOf(Color(android.graphics.Color.parseColor(profile.color))) }
    var host by remember { mutableStateOf(profile.host) }
    var port by remember { mutableStateOf(profile.port.toString()) }
    var expanded by remember { mutableStateOf(false) }

    val colors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow, Color.Black)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "List of Profiles") },
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Update Profile", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(23.dp))

            TextField(
                value = label,
                onValueChange = { label = it },
                label = { Text("Label") }
            )
            TextField(
                value = host,
                onValueChange = { host = it },
                label = { Text("Host") }
            )
            TextField(
                value = port,
                onValueChange = { port = it },
                label = { Text("Port") }
            )
            Text(text = "Seleccionar color:")
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(color, shape = CircleShape)
                    .clickable { expanded = true }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                colors.forEach { newColor ->
                    DropdownMenuItem(
                        text = { Box(Modifier.size(24.dp).background(newColor, shape = CircleShape)) },
                        onClick = {
                            color = newColor
                            expanded = false
                        }
                    )
                }
            }
            Button(onClick = {
                val colorHex = "#%06X".format(0xFFFFFF and color.toArgb())
                viewModel.updateProfile(profile.id, label, colorHex, host, port.toInt())
                navController.popBackStack()
            }) {
                Text("Save Changes")
            }
        }
    }
}