package com.rooms.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.rooms.notes.data.database.NoteDB
import com.rooms.notes.data.repository.NoteRepository
import com.rooms.notes.ui.NoteApp
import com.rooms.notes.ui.theme.NotesTheme
import com.rooms.notes.ui.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            NoteDB::class.java,
            "Notes-Database-Exercici1"
        ).build()

        val dao = db.noteDao()

        val repository = NoteRepository(dao)

        val viewModel = object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return NoteViewModel(repository) as T
            }
        }.create(NoteViewModel::class.java)

        setContent {
            NotesTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    NoteApp(viewModel)
                }
            }
        }
    }
}