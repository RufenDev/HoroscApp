package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("horoscope") val prediction:String

) {
    fun toDomain():PredictionModel{
        return PredictionModel(prediction)
    }
}