package com.savelievoleksandr.diploma.ui.searchCity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.locations.LocationDto
import com.savelievoleksandr.diploma.data.locations.LocationDtoItem

class LocationAdapter(private val location: LocationDto) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_city_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        location?.let { holder.bind(it.get(position), holder.itemView.context) }
    }

    override fun getItemCount() = location.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val imageLocation: ImageView = itemView.findViewById(R.id.imageLocation)
        val labelLocationTV: TextView = itemView.findViewById(R.id.labelLocationTV)
        val regionLocationTV: TextView = itemView.findViewById(R.id.regionLocationTV)
        val propertiesLocationTV: TextView = itemView.findViewById(R.id.propertiesLocationTV)
        fun bind(data: LocationDtoItem, context: Context) {
            labelLocationTV.text = data.label
            regionLocationTV.text = "${data.dest_type}, ${data.region}, ${data.country}"
            propertiesLocationTV.text = "${data.hotels} Properties"
            Glide.with(context)
                .load(data.image_url)
                .centerCrop()
                .error(R.drawable.hotel)
                .into(imageLocation)
        }
    }
}