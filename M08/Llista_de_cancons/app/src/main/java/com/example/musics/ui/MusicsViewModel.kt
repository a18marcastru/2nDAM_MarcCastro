package com.example.musics.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musics.ui.data.getMusicsFromApi
import com.example.musics.ui.model.MusicsResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MusicsViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(MusicsResponse())
    val uiSate: StateFlow<MusicsResponse> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getMusicsFromApi { musicsResponse ->
                musicsResponse?.let {
                    _uiState.update { currentState ->
                        currentState.copy(musics = musicsResponse.musics)
                    }
                }
            }
        }
    }
}