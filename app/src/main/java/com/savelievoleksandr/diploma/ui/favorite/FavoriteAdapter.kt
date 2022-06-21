package com.savelievoleksandr.diploma.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.Favorite


class FavoriteAdapter(private val favorite: ArrayList<Favorite>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int) {
        holder.bind(favorite[position], holder.itemView.context)
    }

    override fun getItemCount() = favorite.size
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val favHotelPhoto: ImageView = itemView.findViewById(R.id.favHotelPhoto)
        private val favHotelName:TextView=itemView.findViewById(R.id.favHotelName)
        private val favCityName:TextView=itemView.findViewById(R.id.favCityName)
        private val favAddress:TextView=itemView.findViewById(R.id.favAddress)
        fun bind(data: Favorite, context: Context) {
            favHotelName.text=data.hotelName
            favCityName.text=data.city
            favAddress.text=data.address
            Glide.with(context)
                .load(data.max_photo_url)
                .centerCrop()
                .error(R.drawable.hotel)
                .into(favHotelPhoto)
        }
    }
}