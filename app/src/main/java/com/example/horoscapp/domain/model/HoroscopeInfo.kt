package com.example.horoscapp.domain.model

import com.example.horoscapp.R.drawable.*
import com.example.horoscapp.R.string.*

sealed class HoroscopeInfo(val img: Int, val name: Int) {

    data object Aquarius : HoroscopeInfo(aquarius_img, aquarius)

    data object Aries : HoroscopeInfo(aries_img, aries)

    data object Cancer : HoroscopeInfo(cancer_img, cancer)

    data object Capricorn : HoroscopeInfo(capricorn_img, capricorn)

    data object Taurus : HoroscopeInfo(taurus_img, taurus)

    data object Gemini : HoroscopeInfo(gemini_img, gemini)

    data object Leo : HoroscopeInfo(leo_img, leo)

    data object Virgo : HoroscopeInfo(virgo_img, virgo)

    data object Libra : HoroscopeInfo(libra_img, libra)

    data object Scorpio : HoroscopeInfo(scorpio_img, scorpio)

    data object Sagittarius : HoroscopeInfo(sagittarius_img, sagittarius)

    data object Pisces : HoroscopeInfo(pisces_img, pisces)

}