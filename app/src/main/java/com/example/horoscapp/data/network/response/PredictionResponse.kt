package com.example.horoscapp.data.network.response

import com.example.horoscapp.domain.model.PredictionModel
import com.google.gson.annotations.SerializedName

//{
//    "date": "2023-10-08",
//    "horoscope": "8 de octubre de 2023 - La acción debe ser su principal prioridad, especialmente cuando se trata de amor y romance.Es posible que no esté demasiado preocupado por qué tipo de acción tome.Su única preocupación es que no esté sentado en ningún momento.Una vez que descubres lo que quieres, es poco probable que te detengas hasta que lo obtengas.Tu poder es contundente y extremo.",
//    "icon": "https://newastro.vercel.app/static/assets/zodiac-2.png",
//    "id": 2,
//    "sign": "Taurus"
//}

data class PredictionResponse(
    @SerializedName("horoscope") val prediction:String
) {
    fun toDomain():PredictionModel{
        return PredictionModel(prediction)
    }
}