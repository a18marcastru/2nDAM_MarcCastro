package com.example.microservicesmanager.data

import android.util.Log
import com.example.microservicesmanager.model.MicroservicesResponse
import com.example.microservicesmanager.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//fun getMicroservicesFromApi(onProcessesLoaded: (MicroservicesResponse?) -> Unit) {
//    val call = RetrofitInstance.api.getMicroservices()
//
//    call.enqueue(object : Callback<MicroservicesResponse> {
//        override fun onResponse(call: Call<MicroservicesResponse>, response: Response<MicroservicesResponse>) {
//            if (response.isSuccessful) {
//                onProcessesLoaded(response.body())
//            } else {
//                Log.e("Process", "Error en la respuesta: ${response.code()}")
//                onProcessesLoaded(null)
//            }
//        }
//
//        override fun onFailure(call: Call<MicroservicesResponse>, t: Throwable) {
//            Log.e("Process", "Error de conexión: ${t.message}")
//            onProcessesLoaded(null)
//        }
//    })
//}