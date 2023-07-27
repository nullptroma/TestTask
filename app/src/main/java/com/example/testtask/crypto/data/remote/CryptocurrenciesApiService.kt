package com.example.testtask.crypto.data.remote

import com.google.gson.JsonObject
import retrofit2.http.GET

interface CryptocurrenciesApiService {
    @GET("global")
    suspend fun getObject(): RemoteObject
}