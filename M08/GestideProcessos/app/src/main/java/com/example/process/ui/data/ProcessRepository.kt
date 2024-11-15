package com.example.process.ui.data

import android.util.Log
import com.example.process.ui.model.ProcessesResponse
import com.example.process.ui.model.StartFunctionRequest
import com.example.process.ui.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getProcesses(onProcessesLoaded: (ProcessesResponse?) -> Unit) {
    val call = RetrofitInstance.api.getProcesses()

    call.enqueue(object : Callback<ProcessesResponse> {
        override fun onResponse(call: Call<ProcessesResponse>, response: Response<ProcessesResponse>) {
            if (response.isSuccessful) {
                onProcessesLoaded(response.body())
            } else {
                Log.e("Process", "Error en la respuesta: ${response.code()}")
                onProcessesLoaded(null)
            }
        }

        override fun onFailure(call: Call<ProcessesResponse>, t: Throwable) {
            Log.e("Process", "Error de conexión: ${t.message}")
            onProcessesLoaded(null)
        }
    })
}

fun postFunctions(request: StartFunctionRequest) {
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