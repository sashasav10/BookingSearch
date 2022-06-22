package com.savelievoleksandr.diploma.ui.searchCity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.locations.LocationDtoItem

interface OnLocationClick {
    fun onClick(
        locationId: Int,
        label: String,
        dest_type: String
    )
}

class LocationAdapter(val locationClick: OnLocationClick) :
    ListAdapter<LocationDtoItem, LocationAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<LocationDtoItem>() {


        override fun areContentsTheSame(
            oldItem: LocationDtoItem,
            newItem: LocationDtoItem
        ): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: LocationDtoItem, newItem: LocationDtoItem): Boolean =
            oldItem.dest_id == newItem.dest_id
    }) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_city_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), holder.itemView.context)
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val imageLocation: ImageView = itemView.findViewById(R.id.imageLocation)
        private val labelLocationTV: TextView = itemView.findViewById(R.id.labelLocationTV)
        private val
                regionLocationTV: TextView = itemView.findViewById(R.id.regionLocationTV)
        private val propertiesLocationTV: TextView =
            itemView.findViewById(R.id.propertiesLocationTV)

        fun bind(data: LocationDtoItem, context: Context) {
            itemView.setOnClickListener {
                locationClick.onClick(data.dest_id, data.label, data.dest_type)
            }
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