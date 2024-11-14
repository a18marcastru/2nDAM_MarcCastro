package com.example.process.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.process.ui.ProcessViewModel
import com.example.process.ui.model.Api
import com.example.process.ui.model.Mongo
import com.example.process.ui.model.Mysql

@Composable
fun ProcessScreen(apis: Api, mongo: Mongo, mysql: Mysql, viewModel: ProcessViewModel) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Row() {
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = apis.title)
            Button(onClick = {
                viewModel.Start(apis.title)
            }) {
                Text(text = apis.button1)
            }
            Button(onClick = {
            }) {
                Text(text = apis.button2)
            }
            Button(onClick = {
            }) {
                Text(text = apis.button3)
            }
        }
        Row() {
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = mongo.title)
            Button(onClick = {
                viewModel.Start(mongo.title)
            }) {
                Text(text = apis.button1)
            }
            Button(onClick = {}) {
                Text(text = apis.button2)
            }
            Button(onClick = {}) {
                Text(text = apis.button3)
            }
        }
        Row() {
            Text(modifier = Modifier.padding(horizontal = 16.dp), text = mysql.title)
            Button(onClick = {
                viewModel.Start(mysql.title)
            }) {
                Text(text = apis.button1)
            }
            Button(onClick = {}) {
                Text(text = apis.button2)
            }
            Button(onClick = {}) {
                Text(text = apis.button3)
            }
        }
    }
}