package com.rooms.notes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String
)

data class Notes(
    var notes: List<Note> = emptyList()
)