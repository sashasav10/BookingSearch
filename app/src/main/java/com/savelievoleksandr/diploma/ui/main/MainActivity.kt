package com.savelievoleksandr.diploma.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.ui.filter.FilterActivity
import com.savelievoleksandr.diploma.ui.searchCity.SearchCityActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var popularHotelAdapter: PopularHotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.mostPopularRecyclerView)
        val citySearch: TextInputLayout = findViewById(R.id.inputCityBtn)
        val filterButton: ImageButton = findViewById(R.id.filterButton)
        citySearch.setOnClickListener {
            val intent1 = Intent(this, SearchCityActivity::class.java)
            intent.putExtra("checkoutDate", "").putExtra("checkinDate", "")
                .putExtra("room", 1).putExtra("adult", 2)
            .putExtra("children", 0)
            startActivity(intent1)
        }
        filterButton.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            intent.putExtra("locationId", 0).putExtra("dest_type", "")
                .putExtra("label", "Choose city").putExtra("room", 1)
                .putExtra("adult", 2).putExtra("children", 0)
                .putExtra("checkoutDate", "").putExtra("checkinDate", "")
            startActivity(intent)
        }
        viewModel.popularHotelResult.observe(this) {
            recyclerView.adapter = PopularHotelAdapter(it)
        }
    }
}