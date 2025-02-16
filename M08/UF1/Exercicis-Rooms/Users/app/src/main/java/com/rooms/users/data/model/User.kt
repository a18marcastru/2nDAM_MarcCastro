package com.rooms.users.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val name: String,
    val age: Int = 0,
    val email: String,
    val password: String
)

data class UserList(
    var userlist: List<User> = emptyList()
)