package com.example.horoscapp.data

import android.util.Log
import com.example.horoscapp.data.network.HoroscopeAPIService
import com.example.horoscapp.domain.Repository
import com.example.horoscapp.domain.model.PredictionModel
import java.util.Locale
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeAPIService) : Repository {

    override suspend fun getPredictions(sign: String): PredictionModel? {

        runCatching {
            val language = Locale.getDefault().language
            apiService.getHoroscopeDetails(sign, language)
        }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("ññ", "Ocurrió un error: ${it.message}") }

        return null
    }
}