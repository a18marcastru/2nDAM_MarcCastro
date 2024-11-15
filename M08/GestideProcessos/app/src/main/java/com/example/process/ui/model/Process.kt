package com.example.process.ui.model

data class Procesos(
    val title: String,
    val button1: String,
    val button2: String,
    val button3: String
)

data class ProcessesResponse(
    var procesos: List<Procesos> = emptyList()
)

data class StartFunctionRequest(
    val title: String
)