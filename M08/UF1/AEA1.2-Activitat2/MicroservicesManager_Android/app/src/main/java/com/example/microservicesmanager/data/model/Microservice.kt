package com.example.microservicesmanager.data.model

data class Microservice(
    val title: String,
    val button1: String,
    val button2: String,
    val button3: String,
    val activated: Int
)

data class MicroservicesResponse(
    var microservices: List<Microservice> = emptyList()
)

data class StartMicroserviceRequest(
    val title: String,
    val activated: Int
)