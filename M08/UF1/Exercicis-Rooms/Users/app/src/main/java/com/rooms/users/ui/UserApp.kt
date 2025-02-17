package com.rooms.users.ui

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rooms.users.R
import com.rooms.users.ui.screens.LoginScreen
import com.rooms.users.ui.screens.ProfileScreen
import com.rooms.users.ui.screens.RegisterScreen
import com.rooms.users.ui.viewmodel.UserViewModel

enum class UserApp(@StringRes val title: Int) {
    Login(title = R.string.login),
    Register(title = R.string.register),
    Profile(title = R.string.profile)
}

@Composable
fun UserApp(viewModel: UserViewModel) {
    val uiStateUser by viewModel.uiStateUser.collectAsState()
    val navHostController = rememberNavController()

    LaunchedEffect(uiStateUser) {
        Log.d("User", uiStateUser.userlist.toString())
        if (uiStateUser.userlist.isNotEmpty()) {
            navHostController.navigate(UserApp.Profile.name) {
                popUpTo(UserApp.Login.name) { inclusive = true }
            }
        }
    }

    NavHost(navHostController, startDestination = UserApp.Login.name) {
        composable(route = UserApp.Login.name) {
            LoginScreen(viewModel, navHostController)
        }
        composable(route = UserApp.Register.name) {
            RegisterScreen(viewModel)
        }
        composable(route = UserApp.Profile.name) {
            ProfileScreen(viewModel, uiStateUser, navHostController)
        }
    }
}
