package com.example.microservicesmanager.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.microservicesmanager.R
import com.example.microservicesmanager.ui.screens.MicroserviceScreen
import com.example.microservicesmanager.ui.screens.ProfilesScreen

enum class MicroserviceApp(@StringRes val title: Int) {
    Menu(title = R.string.menu),
    Profiles(title = R.string.profiles)
}

@Composable
fun MicroserviceApp(navController: NavHostController = rememberNavController(), viewModel: MicroserviceViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val microservices = uiState.microservices
    val uiStateLogs by viewModel.uiStateLogs.collectAsState()
    val logs = uiStateLogs.logs

    NavHost(navController, startDestination = MicroserviceApp.Menu.name) {
        composable(route = MicroserviceApp.Menu.name) {
            MicroserviceScreen(microservices, logs, viewModel, navController)
        }
        composable(route = MicroserviceApp.Profiles.name) {
            ProfilesScreen(navController)
        }
    }
}