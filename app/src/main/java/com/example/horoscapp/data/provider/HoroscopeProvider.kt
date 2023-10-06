package com.example.horoscapp.data.provider

import com.example.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {

    fun getHoroscope() = listOf(
        Aquarius, Aries, Cancer, Capricorn, Taurus, Gemini, Leo,
        Virgo, Libra, Scorpio, Sagittarius, Pisces
    )
}