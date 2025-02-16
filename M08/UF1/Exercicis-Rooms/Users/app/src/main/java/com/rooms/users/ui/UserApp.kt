package com.rooms.users.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rooms.users.R
import com.rooms.users.ui.screens.LoginScreen
import com.rooms.users.ui.screens.RegisterScreen
import com.rooms.users.ui.viewmodel.UserViewModel

enum class UserApp(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.register),
    Profile(title = R.string.profile)
}

@Composable
fun UserApp(viewModel: UserViewModel, navHostController: NavHostController = rememberNavController()) {

    NavHost(navHostController, startDestination = UserApp.Login.name) {
        composable(route = UserApp.Login.name) {
            LoginScreen(navHostController)
        }
        composable(route = UserApp.Register.name) {
            RegisterScreen(viewModel)
        }
    }
}