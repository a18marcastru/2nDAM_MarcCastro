package com.example.musics.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musics.data.deleteMusicIdFromApi
import com.example.musics.data.getMusicsFromApi
import com.example.musics.data.postNewMusicFromApi
import com.example.musics.data.updateMusicIdFromApi
import com.example.musics.model.Music
import com.example.musics.model.MusicRequest
import com.example.musics.model.MusicsResponse
import com.example.musics.model.UpdateMusicRequest
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter

class MusicsViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MusicsResponse())
    val uiSate: StateFlow<MusicsResponse> = _uiState.asStateFlow()
    lateinit var mSocket: Socket

    init {
        viewModelScope.launch {
            getMusicsFromApi { musicsResponse ->
                Log.d("Music", musicsResponse.toString())
                musicsResponse?.let {
                    _uiState.update { currentState ->
                        currentState.copy(musics = musicsResponse.musics)
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
                mSocket.on("newMusic", onNewMusic)
                mSocket.on("deleteMusic", onDeleteMusic)
                mSocket.on("updateMusic", onUpdateMusic)
            }
            mSocket.on(Socket.EVENT_DISCONNECT) {
                Log.d("SocketIO", "Disconnected from socket")
            }
        }
    }

    private val onNewMusic = Emitter.Listener { args ->
        val data = args[0] as String
        val newMusic = Gson().fromJson(data, Music::class.java)

        _uiState.update { currentState ->
            currentState.copy(musics = currentState.musics + newMusic)
        }
    }

    private val onUpdateMusic = Emitter.Listener { args ->
        val data = args[0] as String
        val updatedMusic = Gson().fromJson(data, Music::class.java)

        _uiState.update { currentState ->
            currentState.copy(
                musics = currentState.musics.map { music ->
                    if (music.id == updatedMusic.id) {
                        music.copy(name = updatedMusic.name)
                    } else {
                        music
                    }
                }
            )
        }
        Log.d("data", _uiState.value.toString())
    }
    
    private val onDeleteMusic = Emitter.Listener { args ->
        val id = args[0] as Int

        _uiState.update { currentState ->
            currentState.copy(musics = currentState.musics.filter { it.id != id })
        }
    }

    fun addMusic(newMusic: String) {
        val request = MusicRequest(title = newMusic)
        viewModelScope.launch {
            postNewMusicFromApi(request)
        }
    }

//    fun updateMusic(musicId: Int, musicName: String) {
//        var updateMusic = UpdateMusicRequest(title = musicName)
//        viewModelScope.launch {
//            updateMusicIdFromApi(musicId, updateMusic)
//        }
//    }

    fun deleteMusic(musicId: Int) {
        viewModelScope.launch {
            deleteMusicIdFromApi(musicId)
        }
    }
}