package com.example.microservicesmanager.model

data class Profile(
    val id: Int,
    val label: String,
    val color: String,
    val host: Int,
    val port: Int,
    val predetermined: Boolean
)

data class Profiles(
    var profiles: List<Profile> = emptyList()
)