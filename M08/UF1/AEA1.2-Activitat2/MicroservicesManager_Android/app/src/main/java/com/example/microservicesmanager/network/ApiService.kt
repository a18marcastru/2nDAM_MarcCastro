package com.example.microservicesmanager.network

import com.example.microservicesmanager.model.LogsRequest
import com.example.microservicesmanager.model.LogsResponse
import com.example.microservicesmanager.model.MicroservicesResponse
import com.example.microservicesmanager.model.StartMicroserviceRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ProcessApi by lazy {
        retrofit.create(ProcessApi::class.java)
    }
}

interface ProcessApi {
    @GET("getMicroservices")
    fun getMicroservices(): Call<MicroservicesResponse>

    @POST("postFunctions")
    fun postFunctions(@Body request: StartMicroserviceRequest): Call<Unit>

    @POST("postLogs")
    fun postLogs(@Body request: LogsRequest): Call<LogsResponse>

    @POST("postLogsError")
    fun postLogsError(@Body request: LogsRequest): Call<LogsResponse>
}