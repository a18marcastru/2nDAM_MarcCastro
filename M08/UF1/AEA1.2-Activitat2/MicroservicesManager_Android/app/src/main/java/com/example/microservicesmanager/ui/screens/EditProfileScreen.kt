package com.example.microservicesmanager.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.microservicesmanager.data.model.Profiles
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    navController: NavHostController,
    profileId: String,
    viewModel: MicroserviceViewModel,
    uiStateProfiles: Profiles
) {
    val profile = uiStateProfiles.profiles[profileId.toInt()]

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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = profile.toString())
//            TextField(
//                value = uiStateProfiles,
//                onValueChange = { label = it },
//                label = { Text("Label") }
//            )
//            TextField(
//                value = host,
//                onValueChange = { host = it },
//                label = { Text("Host") }
//            )
//            TextField(
//                value = port,
//                onValueChange = { port = it },
//                label = { Text("Port") }
//            )
//            Text("Predetermined")
//            Checkbox(
//                checked = checked,
//                onCheckedChange = { checked = it }
//            )
//            Button(onClick = {
//                viewModel.updateProfile(profileId, label, color, host, port.toInt(), checked)
//                navController.popBackStack()
//            }) {
//                Text("Save Changes")
//            }
        }
    }
}