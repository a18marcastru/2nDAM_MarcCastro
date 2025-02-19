package com.examen.marc_castro_val2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.examen.marc_castro_val2.ui.viewmodel.Val2ViewModel

@Composable
fun Val2App(viewModel: Val2ViewModel) {
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    val uiStateUser by viewModel.uiStateUser.collectAsState()

    val user = uiStateUser.userlist.firstOrNull()

    if(user == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Login",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido") }
            )

            Row() {
                Button(onClick = { viewModel.login(nombre, apellido) }) {
                    Text(text = "Login")
                }
            }
        }
    }
    else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(uiStateUser.userlist.toString())
        }
    }
}