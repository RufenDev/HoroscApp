package com.example.horoscapp.ui.details

import android.os.Bundle
import android.view.Gravity.*
import android.view.View.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.R.drawable.*
import com.example.horoscapp.R.string.*
import com.example.horoscapp.databinding.ActivityDetailsBinding
import com.example.horoscapp.domain.model.HoroscopeModel.*
import com.example.horoscapp.ui.details.DetailsState.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailsBinding
    private val viewModel by viewModels<DetailsViewModel>()
    private val args:DetailsActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        setSupportActionBar(binding.detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        val image = when(args.modelType){
            AQUARIUS -> aquarius_details
            ARIES -> aries_details
            CANCER -> cancer_details
            CAPRICORN -> capricorn_details
            TAURUS -> taurus_details
            GEMINI -> gemini_details
            LEO -> leo_details
            VIRGO -> virgo_details
            LIBRA -> libra_details
            SCORPIO -> scorpio_details
            SAGITTARIUS -> sagittarius_details
            PISCES -> pisces_details
        }
        binding.detailsAppBarLayout.setBackgroundResource(image)
        
        initUIState()
    }

    private fun initUIState() {
        viewModel.getPrediction(args.modelType.name.lowercase())
        
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    when(it){
                        Loading -> loadingState()
                        is Success -> successState(it)
                        is Error -> errorState(it)
                    }
                }
            }
        }
    }

    private fun loadingState() {
        binding.pbPrediction.visibility = VISIBLE
        binding.tvPredictionTitle.visibility = INVISIBLE
        binding.tvPrediction.visibility = GONE
        binding.tvDetailsTitle.visibility = GONE
    }

    private fun successState(model: Success) {
        binding.tvPrediction.gravity = TOP
        binding.tvPrediction.text = model.horoscope.prediction
        binding.tvPrediction.setTextColor(getColor(R.color.prediction))
        
        val name = when(args.modelType){
            AQUARIUS -> aquarius
            ARIES -> aries
            CANCER -> cancer
            CAPRICORN -> capricorn
            TAURUS -> taurus
            GEMINI -> gemini
            LEO -> leo
            VIRGO -> virgo
            LIBRA -> libra
            SCORPIO -> scorpio
            SAGITTARIUS -> sagittarius
            PISCES -> pisces
        }
        binding.tvDetailsTitle.text = getString(name)

        binding.pbPrediction.visibility = GONE
        binding.tvPredictionTitle.visibility = VISIBLE
        binding.tvPrediction.visibility = VISIBLE
        binding.tvDetailsTitle.visibility = VISIBLE
    }

    private fun errorState(detailsState: Error) {
        binding.tvPrediction.gravity = CENTER_HORIZONTAL
        binding.tvPrediction.text = getString(detailsState.errorID)
        binding.tvPrediction.setTextColor(getColor(R.color.prediction_error))

        binding.pbPrediction.visibility = GONE
        binding.tvPredictionTitle.visibility = INVISIBLE
        binding.tvPrediction.visibility = VISIBLE
        binding.tvDetailsTitle.visibility = GONE
    }

}