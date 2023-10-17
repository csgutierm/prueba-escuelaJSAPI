package com.example.crypto.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EscuelaJSClient {
    companion object {
        private const val BASE_URL = "https://api.escuelajs.co/api/v1/"

        fun retrofitInstance(): EscuelaJSApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(EscuelaJSApi::class.java)
        }
    }
}