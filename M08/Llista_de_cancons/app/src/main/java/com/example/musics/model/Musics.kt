package com.example.musics.model

data class Music(
    val id: Int,
    val name: String
)

data class MusicsResponse(
    var musics: List<Music> = emptyList()
)

data class MusicRequest(
    val title: String
)

data class UpdateMusicRequest(
    val title: String
)