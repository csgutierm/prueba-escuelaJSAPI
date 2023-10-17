package com.example.crypto.data.remote

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface EscuelaJSApi {

/*    @GET("products")
    //suspend fun getData(): Response<Data>
    suspend fun getProductData(): Response<DataJS>*/

    @GET("products")
    fun getProducts(): Call<List<DataJS>>

    @GET("categories")
    fun getCategories(): Call<List<DataJSCategory>>
}