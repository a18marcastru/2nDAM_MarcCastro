package com.example.musics.data

import android.util.Log
import com.example.musics.model.MusicRequest
import com.example.musics.model.MusicsResponse
import com.example.musics.model.UpdateMusicRequest
import com.example.musics.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getMusicsFromApi(onProcessesLoaded: (MusicsResponse?) -> Unit) {
    val call = RetrofitInstance.api.getMusics()

    call.enqueue(object : Callback<MusicsResponse> {
        override fun onResponse(call: Call<MusicsResponse>, response: Response<MusicsResponse>) {
            if (response.isSuccessful) {
                onProcessesLoaded(response.body())
            } else {
                Log.e("Music", "Error en la respuesta: ${response.code()}")
                onProcessesLoaded(null)
            }
        }

        override fun onFailure(call: Call<MusicsResponse>, t: Throwable) {
            Log.e("Music", "Error de conexión: ${t.message}")
            onProcessesLoaded(null)
        }
    })
}

fun postNewMusicFromApi(request: MusicRequest) {
    val call = RetrofitInstance.api.postNewMusic(request)

    call.enqueue(object : Callback<Unit> {
        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            if (response.isSuccessful) {
                Log.d("Music", "Enviat la nova musica")
            } else {
                Log.e("Music", "Music error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Unit>, t: Throwable) {
            Log.e("Music", "Process failed: ${t.message}")
        }
    })
}

fun updateMusicIdFromApi(musicId: Int, updateMusic: UpdateMusicRequest) {
    val call = RetrofitInstance.api.updateMusic(musicId, updateMusic)

    call.enqueue(object : Callback<Unit> {
        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            if (response.isSuccessful) {
                Log.d("Music", "Enviat el començament del proces")
            } else {
                Log.e("Music", "Process error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Unit>, t: Throwable) {
            Log.e("Music", "Process failed: ${t.message}")
        }
    })
}

fun deleteMusicIdFromApi(musicId: Int) {
    val call = RetrofitInstance.api.deleteMusicId(musicId)

    call.enqueue(object : Callback<Unit> {
        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            if (response.isSuccessful) {
                Log.d("Music", "Enviat el començament del proces")
            } else {
                Log.e("Music", "Process error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Unit>, t: Throwable) {
            Log.e("Music", "Process failed: ${t.message}")
        }
    })
}