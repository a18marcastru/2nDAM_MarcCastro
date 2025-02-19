package com.examen.marc_castro_val2.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellido: String
)

data class UserList(
    var userlist: List<User> = emptyList()
)