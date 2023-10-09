package com.example.horoscapp.ui.details

import com.example.horoscapp.domain.model.PredictionModel

sealed class DetailsState {
    data object Loading:DetailsState()

    data class Error(val errorID:Int):DetailsState()

    data class Success(val horoscope: PredictionModel):DetailsState()
}