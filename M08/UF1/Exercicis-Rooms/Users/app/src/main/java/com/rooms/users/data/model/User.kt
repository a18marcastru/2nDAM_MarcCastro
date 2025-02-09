package com.rooms.users.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val age: Int,
    val email: String,
    val password: String
)