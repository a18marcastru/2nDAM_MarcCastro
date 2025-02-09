package com.rooms.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rooms.notes.data.dao.NoteDao
import com.rooms.notes.data.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}