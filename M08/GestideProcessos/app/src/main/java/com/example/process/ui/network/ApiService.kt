package com.example.process.ui.network

import com.example.process.ui.model.ProcessesResponse
import com.example.process.ui.model.StartFunctionRequest
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3010/"

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
    @GET("processes")
    fun getProcesses(): Call<ProcessesResponse>

    @POST("startFunctions")
    fun postFunctions(@Body request: StartFunctionRequest): Call<Unit>
}