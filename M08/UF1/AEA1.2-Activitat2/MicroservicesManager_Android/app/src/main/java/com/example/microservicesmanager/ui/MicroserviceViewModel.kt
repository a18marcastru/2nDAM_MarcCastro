package com.example.microservicesmanager.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microservicesmanager.data.getMicroservicesFromApi
import com.example.microservicesmanager.data.postFunctionsFromApi
import com.example.microservicesmanager.data.postLogsErrorFromApi
import com.example.microservicesmanager.data.postLogsFromApi
import com.example.microservicesmanager.model.LogsRequest
import com.example.microservicesmanager.model.LogsResponse
import com.example.microservicesmanager.model.Microservice
import com.example.microservicesmanager.model.MicroservicesResponse
import com.example.microservicesmanager.model.Profile
import com.example.microservicesmanager.model.Profiles
import com.example.microservicesmanager.model.StartMicroserviceRequest
import kotlinx.coroutines.launch
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONObject
import kotlin.math.log

class MicroserviceViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(MicroservicesResponse())
    val uiState: StateFlow<MicroservicesResponse> = _uiState.asStateFlow()

    private val _uiStateLogs = MutableStateFlow(LogsResponse())
    val uiStateLogs: StateFlow<LogsResponse> = _uiStateLogs.asStateFlow()

    private val _uiStateProfiles = MutableStateFlow(Profiles())
    val uiStateProfiles: StateFlow<Profiles> = _uiStateProfiles.asStateFlow()

    lateinit var mSocket: Socket

    init {
        viewModelScope.launch {
            getMicroservicesFromApi { microservicesResponse ->
                if (microservicesResponse != null) {
                    Log.d("State", microservicesResponse.microservices.toString())
                }
                microservicesResponse?.let {
                    _uiState.update { currentState ->
                        currentState.copy(microservices = microservicesResponse.microservices)
                    }
                }
            }
        }

        viewModelScope.launch {
            try {
                mSocket = IO.socket("http://10.0.2.2:3000")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("SocketIO", "Failed to connect to socket", e)
            }
            mSocket.connect()
            mSocket.on(Socket.EVENT_CONNECT) {
                Log.d("SocketIO", "Connected to socket: ${mSocket.id()}")
                mSocket.on("Activation", onActivation)
            }
            mSocket.on(Socket.EVENT_DISCONNECT) {
                Log.d("SocketIO", "Disconnected from socket")
            }
        }
    }

    private val onActivation = Emitter.Listener { args ->
        val data = args[0] as JSONObject
        val title = data.getString("title")
        val activated = data.getInt("activated")

        _uiState.update { currentState ->
            val newMicroservice: List<Microservice> = currentState.microservices.map { microservice ->
                if (microservice.title == title && activated == 1) {
                    microservice.copy(button1 = "Stop", activated = activated)
                } else if (microservice.title == title && activated == 0) {
                    microservice.copy(button1 = "Start", activated = activated)
                } else microservice
            }

            currentState.copy( microservices = newMicroservice )
        }
    }

    fun functionMicroservice(microservice: String, activation: Int) {
        val request = StartMicroserviceRequest(title = microservice, activation)
        viewModelScope.launch {
            postFunctionsFromApi(request)
        }
    }

    fun callLogs(microservice: String) {
        val request = LogsRequest(title = microservice)
        viewModelScope.launch {
            postLogsFromApi(request) { logsResponse ->
                if (logsResponse != null) {
                    Log.d("State",logsResponse.logs.toString())
                }
                logsResponse?.let {
                    _uiStateLogs.update { currentState ->
                        currentState.copy(logs = logsResponse.logs)
                    }
                }
            }
        }
    }

    fun callLogsError(microservice: String) {
        val request = LogsRequest(title = microservice)
        viewModelScope.launch {
            postLogsErrorFromApi(request) { logsErrorResponse ->
                if (logsErrorResponse != null) {
                    Log.d("State",logsErrorResponse.logs.toString())
                }
                logsErrorResponse?.let {
                    _uiStateLogs.update { currentState ->
                        currentState.copy(logs = logsErrorResponse.logs)
                    }
                }
            }
        }
    }

    fun addProfile(label: String, host: String, port: String, checked: Boolean) {
        val newId = _uiStateProfiles.value.profiles.size + 1
        val newProfile = Profile(
            id = newId,
            label = label,
            color = "",
            host = host.toIntOrNull() ?: 0,
            port = port.toIntOrNull() ?: 0,
            predetermined = checked
        )

        _uiStateProfiles.update { currentProfiles ->
            currentProfiles.copy(profiles = currentProfiles.profiles + newProfile)
        }
    }
}

