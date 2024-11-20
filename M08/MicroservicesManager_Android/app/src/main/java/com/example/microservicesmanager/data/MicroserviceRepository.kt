package com.example.microservicesmanager.data

import android.util.Log
import com.example.microservicesmanager.model.LogsResponse
import com.example.microservicesmanager.model.MicroservicesResponse
import com.example.microservicesmanager.model.StartMicroserviceRequest
import com.example.microservicesmanager.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getMicroservicesFromApi(onProcessesLoaded: (MicroservicesResponse?) -> Unit) {
    val call = RetrofitInstance.api.getMicroservices()

    call.enqueue(object : Callback<MicroservicesResponse> {
        override fun onResponse(call: Call<MicroservicesResponse>, response: Response<MicroservicesResponse>) {
            if (response.isSuccessful) {
                onProcessesLoaded(response.body())
            } else {
                Log.e("Process", "Error en la respuesta: ${response.code()}")
                onProcessesLoaded(null)
            }
        }

        override fun onFailure(call: Call<MicroservicesResponse>, t: Throwable) {
            Log.e("Process", "Error de conexión: ${t.message}")
            onProcessesLoaded(null)
        }
    })
}

fun postFunctionsFromApi(request: StartMicroserviceRequest) {
    val call = RetrofitInstance.api.postFunctions(request)

    call.enqueue(object : Callback<Unit> {
        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
            if (response.isSuccessful) {
                Log.e("Process", "Enviat el començament del proces")
            } else {
                Log.e("Process", "Process error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Unit>, t: Throwable) {
            Log.e("Process", "Process failed: ${t.message}")
        }
    })
}

fun postLogsFromApi(request: String) {
    val call = RetrofitInstance.api.postLogs(request)

    call.enqueue(object : Callback<LogsResponse> {
        override fun onResponse(call: Call<LogsResponse>, response: Response<LogsResponse>) {
            if (response.isSuccessful) {
                Log.e("Process", "Enviat el començament del proces")
            } else {
                Log.e("Process", "Process error: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<LogsResponse>, t: Throwable) {
            Log.e("Process", "Process failed: ${t.message}")
        }
    })
}