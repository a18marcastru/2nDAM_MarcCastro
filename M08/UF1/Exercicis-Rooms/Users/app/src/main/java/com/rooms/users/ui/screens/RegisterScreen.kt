package com.rooms.users.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rooms.users.ui.viewmodel.UserViewModel

@Composable
fun RegisterScreen(viewModel: UserViewModel) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    Column() {
        Text(
            text = "Register",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = name,
            onValueChange = { name = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = age,
            onValueChange = { age = it }
        )

        TextField(
            value = email,
            onValueChange = { email = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { viewModel.newUser(name, age, email, password) }) {
            Text("Register")
        }
    }
}