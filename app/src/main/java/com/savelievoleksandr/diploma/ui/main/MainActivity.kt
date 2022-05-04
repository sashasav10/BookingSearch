package com.savelievoleksandr.diploma.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.ui.searchCity.SearchCityActivity

class MainActivity : AppCompatActivity(){
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var popularHotelAdapter: PopularHotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.mostPopularRecyclerView)
        val citySearch: TextInputLayout = findViewById(R.id.inputCityBtn)
        citySearch.setOnClickListener {
            val intent1 = Intent(this, SearchCityActivity::class.java)
            startActivity(intent1)
        }
        viewModel.popularHotelResult.observe(this) {
            recyclerView.adapter = PopularHotelAdapter(it)
        }
    }
}