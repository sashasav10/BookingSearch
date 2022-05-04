package com.savelievoleksandr.diploma.ui.searchCity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivitySearchCityBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding

class SearchCityActivity :
    GeneralBinding<ActivitySearchCityBinding>(ActivitySearchCityBinding::inflate) {
    private val viewModel by viewModels<SearchCityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)
        val recyclerView: RecyclerView = findViewById(R.id.cityResultRecyclerView)
        val inputCity: EditText = findViewById(R.id.inputCity)
        inputCity.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        viewModel.getLocation(s.toString())
                        viewModel.locationResult.observe(this@SearchCityActivity) {
                            recyclerView.adapter = LocationAdapter(it)
                        }
                    },
                    1000
                )

            }
        })


    }
}