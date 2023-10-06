package com.example.horoscapp.ui.horoscope.adapter

import android.graphics.drawable.RippleDrawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.horoscapp.R
import com.example.horoscapp.databinding.ItemHoroscopeBinding
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(view: View) : ViewHolder(view) {

    private val binding = ItemHoroscopeBinding.bind(view)

    fun render(horoscope: HoroscopeInfo, onHoroscopeSelected: (HoroscopeInfo) -> Unit) {
        val context = itemView.context
        binding.tvHoroscopeName.text = context.getString(horoscope.name)

        binding.btnHoroscope.apply {

            val ripple = getDrawable(context, R.drawable.ripple_horoscope) as RippleDrawable
            ripple.setDrawableByLayerId(R.id.rippleDrawable, getDrawable(context, horoscope.img))
            background = ripple

            setOnClickListener { onHoroscopeSelected(horoscope) }
        }
    }
}