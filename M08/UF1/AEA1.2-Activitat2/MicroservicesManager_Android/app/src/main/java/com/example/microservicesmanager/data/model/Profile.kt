package com.example.microservicesmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    @PrimaryKey (autoGenerate = true) val id: Int = 0,
    val label: String,
    val color: String,
    val host: String,
    val port: Int,
    val predetermined: Boolean
)

data class Profiles(
    var profiles: List<Profile> = emptyList()
)