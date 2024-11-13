package com.example.process.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.process.ui.model.Api
import com.example.process.ui.model.Mongo
import com.example.process.ui.model.Mysql

@Composable
fun ProcessScreen(apis: Api, mongo: Mongo, mysql: Mysql) {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = apis.title)
        Text(text = mongo.title)
        Text(text = mysql.title)
    }
}