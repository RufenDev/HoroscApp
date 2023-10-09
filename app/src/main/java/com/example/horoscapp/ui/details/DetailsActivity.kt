package com.example.horoscapp.ui.details

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ActivityDetailsBinding
import com.example.horoscapp.domain.model.HoroscopeModel
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
        
        val image = when(args.modelType){
            AQUARIUS -> R.drawable.aquarius_details
            ARIES -> R.drawable.aries_details
            CANCER -> R.drawable.cancer_details
            CAPRICORN -> R.drawable.capricorn_details
            TAURUS -> R.drawable.taurus_details
            GEMINI -> R.drawable.gemini_details
            LEO -> R.drawable.leo_details
            VIRGO -> R.drawable.virgo_details
            LIBRA -> R.drawable.libra_details
            SCORPIO -> R.drawable.scorpio_details
            SAGITTARIUS -> R.drawable.sagittarius_details
            PISCES -> R.drawable.pisces_details
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
        binding.pbPrediction.visibility = View.VISIBLE
        binding.tvPrediction.visibility = View.GONE
    }

    private fun successState(detailsState: Success) {
        binding.pbPrediction.visibility = View.GONE
        binding.tvPrediction.visibility = View.VISIBLE

        binding.tvPrediction.text = detailsState.predictionModel.prediction
    }

    private fun errorState(detailsState: Error) {
        binding.pbPrediction.visibility = View.GONE
        binding.tvPrediction.visibility = View.VISIBLE

        binding.tvPrediction.text = getString(detailsState.errorID)
    }

}