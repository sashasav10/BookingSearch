package com.savelievoleksandr.diploma.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.HotelDto
import com.savelievoleksandr.diploma.data.ResultDto

interface OnMostPopularClick {
    fun onClick(hotelId: Int)
}

class PopularHotelAdapter(private val hotels: HotelDto) :
    RecyclerView.Adapter<PopularHotelAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.most_popular_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        hotels.result.let { holder.bind(it[position], holder.itemView.context) }
    }

    override fun getItemCount() = hotels.count

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.hotelMPImage)
        private val hotelNameMP: TextView = itemView.findViewById(R.id.hotelNameMP)
        private val cityNameMP: TextView = itemView.findViewById(R.id.cityNameMP)
        fun bind(data: ResultDto, context: Context) {
            if (data.hotel_name.length > 35) {
                hotelNameMP.text = data.hotel_name.substring(0, 35) + "..."
            } else hotelNameMP.text = data.hotel_name
            if (data.city_name_en.length > 35) {
                cityNameMP.text = data.city_name_en.substring(0, 35)+ "..."
            } else cityNameMP.text = data.city_name_en
            Glide.with(context)
                .load(data.max_photo_url)
                .centerCrop()
                .error(R.drawable.hotel)
                .into(icon)
        }
    }
}