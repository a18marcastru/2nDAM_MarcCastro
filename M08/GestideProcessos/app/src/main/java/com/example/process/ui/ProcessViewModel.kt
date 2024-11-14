package com.example.process.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.process.ui.data.postFunctions
import com.example.process.ui.model.Api
import com.example.process.ui.model.Mongo
import com.example.process.ui.model.Mysql
import com.example.process.ui.model.StartFunctionRequest
import kotlinx.coroutines.launch
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter

class ProcessViewModel(): ViewModel() {
    var api = mutableStateOf<Api?>(null)
        private set

    var mongo = mutableStateOf<Mongo?>(null)
        private set

    var mysql = mutableStateOf<Mysql?>(null)
        private set

    lateinit var mSocket: Socket

    init {
        viewModelScope.launch {
            try {
                mSocket = IO.socket("http://10.0.2.2:3010")
                Log.d("SocketIO", "Hola")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SocketIO", "Failed to connect to socket", e)
            }
            mSocket.connect()
            mSocket.on(Socket.EVENT_CONNECT) {
                Log.d("SocketIO", "Connected to socket: ${mSocket.id()}")
            }
            mSocket.on(Socket.EVENT_DISCONNECT) {
                Log.d("SocketIO", "Disconnected from socket")
            }
        }
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

    fun Start(function: String) {
        val request = StartFunctionRequest(title = function)
        viewModelScope.launch {
            postFunctions(request)
        }
    }
}