package com.savelievoleksandr.diploma.ui.hotels

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivityHotelsBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.filter.FilterActivity
import com.savelievoleksandr.diploma.ui.hotelDetailed.DetailedActivity
import com.savelievoleksandr.diploma.ui.searchCity.LocationAdapter
import com.savelievoleksandr.diploma.ui.searchCity.OnLocationClick
import okhttp3.internal.toImmutableList

class HotelActivity :
    GeneralBinding<ActivityHotelsBinding>(ActivityHotelsBinding::inflate), OnHotelClick {
    private val viewModel by viewModels<HotelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)
        val arguments = intent.extras
        val dest_id = arguments?.getInt("locationId")!!.toInt()
        val dest_type = arguments?.getString("dest_type").toString()
        val label = arguments.getString("label").toString()
        val checkoutDate = arguments.getString("checkoutDate").toString()
        val checkinDate = arguments.getString("checkinDate").toString()
        val room = arguments.getInt("room")
        val adult = arguments.getInt("adult")
        val children = arguments.getInt("children")
        Log.i(
            "SASHA",
            "$checkoutDate, $dest_id, $dest_type, $adult, UAH, $checkinDate, $room, children $children"
        )

        val backBtn2: ImageButton = binding.backBtn2
        val cityTV: TextView = binding.cityTV
        cityTV.text = label.split(",")[0]
        backBtn2.setOnClickListener { this.finish() }
        cityTV.setOnClickListener { this.finish() }
        val recyclerView: RecyclerView = findViewById(R.id.hotelRecyclerView)
        viewModel.getHotel(checkoutDate, dest_id, dest_type, adult, checkinDate, room, children)
        viewModel.hotelResult.observe(this) {
            recyclerView.adapter = HotelAdapter(this@HotelActivity,it)
        }
    }

    override fun onClick(
        hotel_id: Int,
        hotel_name: String,
        review_score: Double,
        review_score_word: String,
        address: String,
        distance_to_cc: Double,
        is_free_cancellable: Byte,
        hotel_include_breakfast: Byte,
        min_total_price: Double,
        url: String
    ) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("hotel_id", hotel_id).putExtra("hotel_name", hotel_name)
            .putExtra("review_score", review_score).putExtra("review_score_word", review_score_word)
            .putExtra("address", address).putExtra("distance_to_cc", distance_to_cc)
            .putExtra("is_free_cancellable", is_free_cancellable)
            .putExtra("hotel_include_breakfast", hotel_include_breakfast)
            .putExtra("min_total_price", min_total_price)
            .putExtra("url", url)
        startActivity(intent)
    }
}