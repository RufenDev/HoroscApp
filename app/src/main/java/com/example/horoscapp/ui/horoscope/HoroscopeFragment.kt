package com.example.horoscapp.ui.horoscope

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.horoscapp.databinding.FragmentHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo
import com.example.horoscapp.domain.model.HoroscopeInfo.*
import com.example.horoscapp.domain.model.HoroscopeModel
import com.example.horoscapp.domain.model.HoroscopeModel.*
import com.example.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    private lateinit var horoscopeAdapter: HoroscopeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initRecyclerview()
        initUIState()
    }

    private fun initRecyclerview() {
        horoscopeAdapter = HoroscopeAdapter{onHoroscopeSelected(it)}

        binding.rvHoroscope.apply {
            adapter = horoscopeAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeViewModel.horoscope.collect {
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }

    private fun onHoroscopeSelected(horoscope:HoroscopeInfo){
        val modelType: HoroscopeModel = when(horoscope){
            Aquarius -> AQUARIUS
            Aries -> ARIES
            Cancer ->  CANCER
            Capricorn -> CAPRICORN
            Gemini -> GEMINI
            Leo -> LEO
            Libra -> LIBRA
            Pisces -> PISCES
            Sagittarius -> SAGITTARIUS
            Scorpio -> SCORPIO
            Taurus -> TAURUS
            Virgo -> VIRGO
        }
        findNavController().navigate(
            HoroscopeFragmentDirections.actionHoroscopeFragmentToDetailsActivity(modelType)
        )
    }
}