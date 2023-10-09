package com.example.horoscapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.PredictionModel
import com.example.horoscapp.domain.usecase.GetPredictionUseCase
import com.example.horoscapp.ui.details.DetailsState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPredictionUseCase: GetPredictionUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<DetailsState>(Loading)
    val state: StateFlow<DetailsState> = _state

    fun getPrediction(sign: String) {
        viewModelScope.launch {
            _state.value = Loading
            val result: PredictionModel? = withContext(Dispatchers.IO) { getPredictionUseCase(sign) }

            result?.let {
                _state.value = Success(it)

            } ?: run {
                _state.value = Error(R.string.prediction_error)
            }

        }
    }

}