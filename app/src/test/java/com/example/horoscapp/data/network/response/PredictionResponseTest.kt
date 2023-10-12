package com.example.horoscapp.data.network.response

import com.example.horoscapp.motherobject.HoroscopeMotherObject.anyResponse
import io.kotlintest.shouldBe
import org.junit.Test

class PredictionResponseTest{

    @Test
    fun `toDomain should return a correct PredictionModel`(){

        //Give
        val predictionResponse = anyResponse
//        val predictionResponse = anyResponse.copy("Predicción para un Libra")

        //When
        val predictionModel = predictionResponse.toDomain()

        //Then
        predictionModel.prediction shouldBe predictionResponse.prediction

    }
}