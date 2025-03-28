package com.example.microservicesmanager.ui.screens

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
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.microservicesmanager.data.model.Profile
import com.example.microservicesmanager.ui.MicroserviceApp
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListProfilesScreen(profiles: List<Profile>, navController: NavController, viewModel: MicroserviceViewModel) {
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Spacer(modifier = Modifier.height(23.dp))
                Text("Profiles", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(23.dp))

                Button(onClick = { navController.navigate(MicroserviceApp.NewProfile.name) }) {
                    Text("New Profile")
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

            items(profiles) { profile ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = profile.label, style = MaterialTheme.typography.bodyMedium)

                    val parsedColor = try {
                        if (profile.color.isNotBlank()) {
                            Color(android.graphics.Color.parseColor(profile.color))
                        } else {
                            Color.Gray
                        }
                    } catch (e: IllegalArgumentException) {
                        Color.Gray
                    }

                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = parsedColor, shape = CircleShape)
                    )

                    Text(text = profile.host, style = MaterialTheme.typography.bodyMedium)
                    Text(text = profile.port.toString(), style = MaterialTheme.typography.bodyMedium)
                    Checkbox(
                        checked = profile.predetermined,
                        onCheckedChange = { viewModel.togglePredetermined(profile) }
                    )

                    IconButton(onClick = { navController.navigate("EditProfile/${profile.id}") }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit profile"
                        )
                    }
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