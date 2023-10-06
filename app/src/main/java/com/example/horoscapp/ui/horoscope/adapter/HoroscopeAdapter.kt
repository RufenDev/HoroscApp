package com.example.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopeAdapter(
    private var horoscopeList: List<HoroscopeInfo> = emptyList(),
    private val onHoroscopeSelected: (HoroscopeInfo) -> Unit
) : Adapter<HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HoroscopeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
    )

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onHoroscopeSelected)
    }

    override fun getItemCount() = horoscopeList.size

    fun updateList(newList:List<HoroscopeInfo>){
        horoscopeList = newList
        notifyDataSetChanged()
    }
}