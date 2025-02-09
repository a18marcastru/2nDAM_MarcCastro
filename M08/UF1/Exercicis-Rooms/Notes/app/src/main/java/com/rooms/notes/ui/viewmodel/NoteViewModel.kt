package com.rooms.notes.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rooms.notes.data.model.Notes
import com.rooms.notes.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
    private val _uiStateNotes = MutableStateFlow(Notes())
    val uiStateNotes: StateFlow<Notes> = _uiStateNotes.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val notes = repository.getNotesAll()
                if(notes != null) {
                    _uiStateNotes.update { currentState ->
                        currentState.copy(notes = notes)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun newNote(note: String) {
        viewModelScope.launch {
            repository.insertNote(note)

            try {
                val notes = repository.getNotesAll()
                if(notes != null) {
                    _uiStateNotes.update { currentState ->
                        currentState.copy(notes = notes)
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteNote(id)
                _uiStateNotes.update { currentState ->
                    currentState.copy(notes = currentState.notes.filter { it.id != id })
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
    }
}