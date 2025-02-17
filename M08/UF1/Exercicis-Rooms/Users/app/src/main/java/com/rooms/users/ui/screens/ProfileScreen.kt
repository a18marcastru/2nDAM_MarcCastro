package com.rooms.users.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.rooms.users.data.model.UserList
import com.rooms.users.ui.UserApp
import com.rooms.users.ui.viewmodel.UserViewModel

@Composable
fun ProfileScreen(
    viewModel: UserViewModel,
    uiStateUser: UserList,
    navHostController: NavHostController
) {
    val user = uiStateUser.userlist.firstOrNull() // Obtiene el primer usuario de la lista

    Log.d("User", user.toString())

    user?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Profile", style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Name: ${it.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Age: ${it.age}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Email: ${it.email}", style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    viewModel.logout(it.id)
                    navHostController.navigate(UserApp.Login.name) {
                        popUpTo(UserApp.Profile.name) { inclusive = true }
                    }
                }) {
                    Text("Logout")
                }
                Button(onClick = {
                    viewModel.deleteAccount(it.id)
                    navHostController.navigate(UserApp.Login.name) {
                        popUpTo(UserApp.Profile.name) { inclusive = true }
                    }
                }) {
                    Text("Delete my account")
                }
            }

        }
    }
}