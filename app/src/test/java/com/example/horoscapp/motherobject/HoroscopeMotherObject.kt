package com.example.horoscapp.motherobject

import com.example.horoscapp.data.network.response.PredictionResponse
import com.example.horoscapp.domain.model.HoroscopeInfo

object HoroscopeMotherObject {

    val anyResponse = PredictionResponse("Predicción para un tauro")

    val horoscopeInfoList = listOf(
        HoroscopeInfo.Aquarius,
        HoroscopeInfo.Aries,
        HoroscopeInfo.Cancer,
        HoroscopeInfo.Capricorn,
        HoroscopeInfo.Taurus,
        HoroscopeInfo.Gemini,
        HoroscopeInfo.Leo,
        HoroscopeInfo.Virgo,
        HoroscopeInfo.Libra,
        HoroscopeInfo.Scorpio,
        HoroscopeInfo.Sagittarius,
        HoroscopeInfo.Pisces
    )
}