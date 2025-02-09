package com.rooms.notes.data.repository

import com.rooms.notes.data.dao.NoteDao
import com.rooms.notes.data.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NoteRepository(private val dao: NoteDao) {
    suspend fun insertNote(note: String) {
        withContext(Dispatchers.IO) {
            dao.insertNote(Note(
                text = note
            ))
        }
    }

    suspend fun getNotesAll(): List<Note>? {
        return withContext(Dispatchers.IO) {
            dao.getNotesAll()
        }
    }

    suspend fun deleteNote(id: Int) {
        withContext(Dispatchers.IO) {
            dao.deleteNote(id)
        }
    }
}