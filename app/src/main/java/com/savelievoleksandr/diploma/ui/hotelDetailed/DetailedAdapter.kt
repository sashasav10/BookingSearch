package com.savelievoleksandr.diploma.ui.hotelDetailed

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.photo.PhotoDto
import com.savelievoleksandr.diploma.data.photo.PhotoDtoItem

class DetailedAdapter(private val photos: PhotoDto) :
    RecyclerView.Adapter<DetailedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DetailedAdapter.ViewHolder, position: Int) {
        holder.bind(photos[position], holder.itemView.context)
    }

    override fun getItemCount() = photos.size
    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.hotelPhoto)
        fun bind(data: PhotoDtoItem, context: Context) {
            Glide.with(context)
                .load(data.url_1440)
                .centerCrop()
                .error(R.drawable.hotel)
                .into(image)
        }
    }
}