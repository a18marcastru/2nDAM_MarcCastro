package com.example.process.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.process.ui.model.Api
import com.example.process.ui.model.Mongo
import com.example.process.ui.model.Mysql

class ProcessViewModel(): ViewModel() {
    var api = mutableStateOf<Api?>(null)
        private set

    var mongo = mutableStateOf<Mongo?>(null)
        private set

    var mysql = mutableStateOf<Mysql?>(null)
        private set

    init {
        api.value = Api(
            title = "APIs",
            button1 = "Start",
            button2 = "logs",
            button3 = "logs error"
        )
        mongo.value = Mongo(
            title = "MongoDB",
            button1 = "Start",
            button2 = "logs",
            button3 = "logs error"
        )
        mysql.value = Mysql(
            title = "MySQL",
            button1 = "Start",
            button2 = "logs",
            button3 = "logs error"
        )
    }
}