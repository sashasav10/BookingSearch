package com.savelievoleksandr.diploma.ui.hotelDetailed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.savelievoleksandr.diploma.databinding.ActivityHotelDetailedBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding


class DetailedActivity :
    GeneralBinding<ActivityHotelDetailedBinding>(ActivityHotelDetailedBinding::inflate) {
    private val viewModel by viewModels<DetailedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val arguments = intent.extras
        val hotel_id = arguments?.getInt("hotel_id")!!.toInt()
        val hotel_name = arguments?.getString("hotel_name").toString()
        val review_score = arguments.getDouble("review_score")
        val review_score_word = arguments.getString("review_score_word").toString()
        val addres = arguments.getString("address").toString()
        val distance_to_cc = arguments.getDouble("distance_to_cc")
        val is_free_cancellable = arguments.getByte("is_free_cancellable")
        val hotel_include_breakfast = arguments.getByte("hotel_include_breakfast")
        val min_total_price = arguments.getDouble("min_total_price")
        val url = arguments.getString("url").toString()
        Log.i("SASHA","hotel_id in activity $hotel_id")

        val hotelName: TextView = binding.hotelName
        val score: TextView = binding.score
        val scoreWord: TextView = binding.scoreWord
        val address: TextView = binding.address
        val distance: TextView = binding.distance
        val cancellation: TextView = binding.cancellation
        val breakfast: TextView = binding.breakfast
        val price: TextView = binding.price
        val bookBtn: Button = binding.bookBtn
        hotelName.text=hotel_name
            score.text=review_score.toString()
            scoreWord.text=review_score_word
            address.text=addres
            distance.text=distance_to_cc.toString()
        if(is_free_cancellable<1){
            cancellation.text="Cancellation is not free"}
        else
        {cancellation.text="Free cancellation"}
        if(hotel_include_breakfast<1){
            breakfast.text="Breakfast is not included"}
        else
        {breakfast.text="Breakfast included"}
            price.text=min_total_price.toString()


        val backBtn3: ImageButton = binding.backBtn3
        backBtn3.setOnClickListener { this.finish() }
        bookBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
        val recyclerView: RecyclerView = binding.photoRecyclerView
        viewModel.getPhoto(hotel_id)

        viewModel.photoResult.observe(this) {
            recyclerView.adapter = DetailedAdapter(it)
        }
    }
}