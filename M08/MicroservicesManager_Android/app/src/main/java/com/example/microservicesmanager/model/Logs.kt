package com.example.microservicesmanager.model

data class Logs(
    val log: String
)

data class LogsResponse(
    var logs: List<Logs> = emptyList()
)