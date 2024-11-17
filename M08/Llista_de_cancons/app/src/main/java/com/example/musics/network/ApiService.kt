package com.example.musics.network

import com.example.musics.model.MusicRequest
import com.example.musics.model.MusicsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3010/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: MusicApi by lazy {
        retrofit.create(MusicApi::class.java)
    }
}

interface MusicApi {
    @GET("musics")
    fun getMusics(): Call<MusicsResponse>

    @POST("newMusic")
    fun postNewMusic(@Body request: MusicRequest): Call<Unit>

    @PUT("updateMusic/{id}")
    fun updateMusic(@Path("id") id: Int, name: String): Call<Unit>

    @DELETE("deleteMusic/{id}")
    fun deleteMusicId(@Path("id") id: Int): Call<Unit>
}