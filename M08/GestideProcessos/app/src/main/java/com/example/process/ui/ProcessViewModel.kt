package com.example.process.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.process.ui.data.getProcesses
import com.example.process.ui.data.postFunctions
import com.example.process.ui.model.Procesos
import com.example.process.ui.model.ProcessesResponse
import com.example.process.ui.model.StartFunctionRequest
import kotlinx.coroutines.launch
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONObject

class ProcessViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(ProcessesResponse())
    val uiSate: StateFlow<ProcessesResponse> = _uiState.asStateFlow()

    lateinit var mSocket: Socket

    init {
        viewModelScope.launch {
            getProcesses { processesResponse ->
                if (processesResponse != null) {
                    Log.d("state", processesResponse.procesos.toString())
                }
                processesResponse?.let {
                    _uiState.update { currentState ->
                        currentState.copy(procesos = processesResponse.procesos)
                    }
                }
            }
        }

        viewModelScope.launch {
            try {
                mSocket = IO.socket("http://10.0.2.2:3010")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SocketIO", "Failed to connect to socket", e)
            }
            mSocket.connect()
            mSocket.on(Socket.EVENT_CONNECT) {
                Log.d("SocketIO", "Connected to socket: ${mSocket.id()}")
                mSocket.on("Activation", onActivation);
                mSocket.on("Logs", onLogs)
            }
            mSocket.on(Socket.EVENT_DISCONNECT) {
                Log.d("SocketIO", "Disconnected from socket")
            }
        }
    }

    private val onActivation = Emitter.Listener { args ->
        val data = args[0] as JSONObject
        val title = data.getString("title")


        _uiState.update { currentState ->
            val newProcesss: List<Procesos> = currentState.procesos.map { proceso ->
                if (proceso.title == title) {
                    proceso.copy(button1 = if (proceso.button1 == "Start") "Stop" else "Start")
                } else {
                    proceso
                }
            }

            currentState.copy( procesos = newProcesss )
        }
        Log.d("SocketIO", "title recieved: $title")
    }

    private val onLogs = Emitter.Listener { args ->

    }

    fun Start(function: String) {
        val request = StartFunctionRequest(title = function)
        viewModelScope.launch {
            postFunctions(request)
        }
    }
}

