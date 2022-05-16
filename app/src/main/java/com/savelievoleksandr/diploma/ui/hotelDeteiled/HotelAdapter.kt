package com.savelievoleksandr.diploma.ui.hotelDeteiled

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

class HotelAdapter(private val hotels: HotelDto) :
    RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HotelAdapter.ViewHolder, position: Int) {
        hotels.result.let { holder.bind(it[position], holder.itemView.context) }
    }

    override fun getItemCount() = hotels.count

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val hotelNameTV: TextView = itemView.findViewById(R.id.hotelNameTV3)
        private val addressTV: TextView = itemView.findViewById(R.id.addressTV)
        private val priceTV: TextView = itemView.findViewById(R.id.priceTV)
        private val descriptionTV: TextView = itemView.findViewById(R.id.descriptionTV)
         private val imageSlider:ImageView = itemView.findViewById(R.id.imageSlider)
        // val imageSlider = itemView.findViewById<ImageSlider>(R.id.imageSlider)
        //val imageList = ArrayList<SlideModel>()
        fun bind(data: ResultDto, context: Context) {
           // imageList.add(SlideModel(data.max_photo_url))
            // imageSlider.setImageList(imageList)
            hotelNameTV.text = data.hotel_name
            addressTV.text = data.address
            priceTV.text=String.format("%.2f",data.min_total_price)+" ${data.currencycode}"
            descriptionTV.text=data.unit_configuration_label
            Glide.with(context)
                .load(data.max_photo_url)
                .centerCrop()
                .error(R.drawable.hotel)
                .into(imageSlider)
        }
    }
}