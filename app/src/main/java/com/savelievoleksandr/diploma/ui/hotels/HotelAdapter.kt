package com.savelievoleksandr.diploma.ui.hotels

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.hotels.HotelDto
import com.savelievoleksandr.diploma.data.hotels.ResultDto

interface OnHotelClick {
    fun onClick(
        hotel_id:Int,
        hotel_name:String,
        city_name:String,
        review_score:Double,
        review_score_word:String,
        address:String,
        distance_to_cc:Double,
        is_free_cancellable:Byte,
        hotel_include_breakfast:Byte,
        min_total_price:Double,
        url:String,
        max_photo_url:String
    )
}

class HotelAdapter(val hotelClick: OnHotelClick?,private val hotels: HotelDto) :
    RecyclerView.Adapter<HotelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hotel_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = hotels.count


    override fun onBindViewHolder(holder: HotelAdapter.ViewHolder, position: Int) {
        hotels.result.let { holder.bind(it[position], holder.itemView.context) }
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val hotelNameTV: TextView = itemView.findViewById(R.id.hotelNameTV3)
        private val addressTV: TextView = itemView.findViewById(R.id.addressTV)
        private val priceTV: TextView = itemView.findViewById(R.id.priceTV)
        private val typeTV: TextView = itemView.findViewById(R.id.typeTV)
        private val descriptionTV: TextView = itemView.findViewById(R.id.descriptionTV)
        private val bedsTV: TextView = itemView.findViewById(R.id.bedsTV)
        private val image: ImageView = itemView.findViewById(R.id.imageSlider)
        fun bind(data: ResultDto, context: Context) {
            itemView.setOnClickListener {
                hotelClick?.onClick(
                    data.hotel_id,
                    data.hotel_name,
                    data.city_name_en,
                    data.review_score,
                    data.review_score_word,
                    data.address,
                    data.distance_to_cc,
                    data.is_free_cancellable,
                    data.hotel_include_breakfast,
                    data.min_total_price,
                    data.url,
                    data.max_photo_url
                )
            }
            Log.i(
                "SASHA",
                "hotel_name ${data.hotel_name}, address ${data.address}"
            )
            val temp = data.unit_configuration_label.split("<br/><b>", "</b>: ").toTypedArray()
            hotelNameTV.text = data.hotel_name
            addressTV.text = data.address
            priceTV.text = String.format("%.2f", data.min_total_price) + " ${data.currencycode}"
            typeTV.text = "• " + temp[0]
            descriptionTV.text = "• " + temp[1]
            bedsTV.text = "• " + temp[2]
            Glide.with(context)
                .load(data.max_photo_url)
                .centerCrop()
                .error(R.drawable.hotel)
                .into(image)
        }
    }
}