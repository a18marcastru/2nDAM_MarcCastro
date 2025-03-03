package com.example.microservicesmanager.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.microservicesmanager.data.repository.getMicroservicesFromApi
import com.example.microservicesmanager.data.repository.postFunctionsFromApi
import com.example.microservicesmanager.data.repository.postLogsErrorFromApi
import com.example.microservicesmanager.data.repository.postLogsFromApi
import com.example.microservicesmanager.data.model.LogsRequest
import com.example.microservicesmanager.data.model.LogsResponse
import com.example.microservicesmanager.data.model.Microservice
import com.example.microservicesmanager.data.model.MicroservicesResponse
import com.example.microservicesmanager.data.model.Profile
import com.example.microservicesmanager.data.model.Profiles
import com.example.microservicesmanager.data.model.StartMicroserviceRequest
import com.example.microservicesmanager.data.repository.ProfileRepository
import kotlinx.coroutines.launch
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.json.JSONObject

class MicroserviceViewModel(private val repository: ProfileRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(MicroservicesResponse())
    val uiState: StateFlow<MicroservicesResponse> = _uiState.asStateFlow()

    private val _uiStateLogs = MutableStateFlow(LogsResponse())
    val uiStateLogs: StateFlow<LogsResponse> = _uiStateLogs.asStateFlow()

    private val _uiStateProfiles = MutableStateFlow(Profiles())
    val uiStateProfiles: StateFlow<Profiles> = _uiStateProfiles.asStateFlow()

    private lateinit var mSocket: Socket

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

            try {
                val profiles = repository.getProfilesAll()
                if(profiles != null) {
                    _uiStateProfiles.update { currentState ->
                        currentState.copy(profiles = profiles)
                    }
                }
                else _uiStateProfiles.value = Profiles(emptyList())
            } catch (e: Exception) {
                Log.e("Error", "Error getting data: ${e.message}", e)
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

    fun addProfile(label: String, host: String, port: String, color: String, checked: Boolean, onSuccess: (String) -> Unit) {
        val newProfile = Profile(
            label = label,
            color = color,
            host = host,
            port = port.toIntOrNull() ?: 0,
            predetermined = checked
        )

        viewModelScope.launch {
            try {
                repository.insertProfile(newProfile)
                val profiles = repository.getProfilesAll()
                if(profiles != null) {
                    onSuccess("New profile success")
                    _uiStateProfiles.update { currentState ->
                        currentState.copy(profiles = profiles)
                    }
                }
                else _uiStateProfiles.value = Profiles(emptyList())
            } catch (e: Exception) {
                Log.e("Error", "Error getting data: ${e.message}", e)
            }
        }
    }

    fun togglePredetermined(selectedProfile: Profile) {
        val newPredeterminedValue = if (selectedProfile.predetermined) 0 else 1

        viewModelScope.launch {
            repository.clearAllPredetermined(0)

            repository.updatePredetermined(selectedProfile.id, newPredeterminedValue)

            val updatedProfiles = repository.getProfilesAll()

            if(updatedProfiles != null) {
                _uiStateProfiles.update { currentState ->
                    currentState.copy(profiles = updatedProfiles);
                }
            }

            Log.d("Profile-Update", _uiStateProfiles.value.profiles.toString())
        }
    }

     fun updateProfile(id: Int, label: String, colorHex: String, host: String, port: Int) {
         viewModelScope.launch {
             try {
                 repository.updateProfile(id, label, colorHex, host, port)

                 val profiles = repository.getProfilesAll()
                 if(profiles != null) {
                     _uiStateProfiles.update { currentState ->
                         currentState.copy(profiles = profiles)
                     }
                 }
                 else _uiStateProfiles.value = Profiles(emptyList())
                 Log.d("Profiles-UPDATE", _uiStateProfiles.value.profiles.toString())
             } catch (e: Exception) {
                 Log.e("Error", e.toString())
             }
        }
    }

    fun deleteProfile(id: Int) {
        Log.d("Profiles", id.toString())

        viewModelScope.launch {
            try {
                repository.deleteProfile(id)
                _uiStateProfiles.update { currentState ->
                    currentState.copy(profiles = currentState.profiles.filter { it.id != id })
                }
            } catch (e: Exception) {
                Log.e("Error", "Error to delete profile: ${e.message}")
            }
        }
    }
}

