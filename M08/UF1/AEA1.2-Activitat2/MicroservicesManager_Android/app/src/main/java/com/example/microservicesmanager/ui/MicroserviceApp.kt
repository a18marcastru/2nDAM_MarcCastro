package com.example.microservicesmanager.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.microservicesmanager.R
import com.example.microservicesmanager.ui.screens.ListProfilesScreen
import com.example.microservicesmanager.ui.screens.MicroserviceScreen
import com.example.microservicesmanager.ui.screens.ProfileScreen
import com.example.microservicesmanager.ui.viewmodel.MicroserviceViewModel

enum class MicroserviceApp(@StringRes val title: Int) {
    Menu(title = R.string.menu),
    ListProfiles(title = R.string.profiles),
    NewProfile(title = R.string.New_Profile)
}

@Composable
fun MicroserviceApp(viewModel: MicroserviceViewModel, navController: NavHostController = rememberNavController()) {
    val uiState by viewModel.uiState.collectAsState()
    val uiStateLogs by viewModel.uiStateLogs.collectAsState()
    val uiStateProfiles by viewModel.uiStateProfiles.collectAsState()

    NavHost(navController, startDestination = MicroserviceApp.Menu.name) {
        composable(route = MicroserviceApp.Menu.name) {
            MicroserviceScreen(uiState.microservices, uiStateLogs.logs, viewModel, navController)
        }
        composable(route = MicroserviceApp.ListProfiles.name) {
            ListProfilesScreen(uiStateProfiles.profiles, navController, viewModel)
        }
        composable(route = MicroserviceApp.NewProfile.name) {
            ProfileScreen(navController, viewModel)
        }
    }
}