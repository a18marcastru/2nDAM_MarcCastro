package com.example.microservicesmanager.data.model

data class Log(
    val log: String
)

data class LogsResponse(
    var logs: List<Log> = emptyList()
)

data class LogsRequest(
    val title: String
)