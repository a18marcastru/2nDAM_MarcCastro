package com.rooms.notes.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.rooms.notes.data.model.Note
import androidx.room.Query

@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    suspend fun getNotesAll() : List<Note>?

    @Insert
    suspend fun insertNote(item: Note)

    @Query("DELETE FROM Notes WHERE id = :id")
    suspend fun deleteNote(id: Int)
}