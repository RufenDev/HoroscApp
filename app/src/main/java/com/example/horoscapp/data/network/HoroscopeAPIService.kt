package com.example.horoscapp.data.network

import com.example.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HoroscopeAPIService {

    @GET("/{sign}")
    suspend fun getHoroscopeDetails(
        @Path("sign") horoscope:String,
        @Query("lang") language:String
    ):PredictionResponse

}