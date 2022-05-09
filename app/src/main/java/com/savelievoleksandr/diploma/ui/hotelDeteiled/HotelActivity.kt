package com.savelievoleksandr.diploma.ui.hotelDeteiled

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivityFilterBinding.inflate
import com.savelievoleksandr.diploma.databinding.ActivityHotelDeteiledBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding

class HotelActivity :
    GeneralBinding<ActivityHotelDeteiledBinding>(ActivityHotelDeteiledBinding::inflate) {
    private val viewModel by viewModels<HotelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_deteiled)
        val arguments = intent.extras
        val dest_id = arguments?.getInt("locationId")!!.toInt()
        val dest_type = arguments?.getString("dest_type").toString()
        val label = arguments.getString("label").toString()
        val checkoutDate = arguments.getString("checkoutDate").toString()
        val checkinDate = arguments.getString("checkinDate").toString()
        val room = arguments.getInt("room")
        val adult = arguments.getInt("adult")
        val children = arguments.getInt("children")
        Log.i("SASHA", "$checkoutDate, $dest_id, $dest_type, $adult, UAH, $checkinDate, $room, children $children")

        val backBtn2: ImageButton = findViewById(R.id.backBtn2)
        val cityTV: TextView = findViewById(R.id.cityTV)
        cityTV.text=label.split(",")[0]
        backBtn2.setOnClickListener { this.finish() }
        cityTV.setOnClickListener { this.finish() }
        val recyclerView: RecyclerView = findViewById(R.id.hotelRecyclerView)
        viewModel.getHotel(checkoutDate, dest_id, dest_type, adult, checkinDate, room, children)

        viewModel.hotelResult.observe(this) {
            recyclerView.adapter = HotelAdapter(it)
        }
    }
}