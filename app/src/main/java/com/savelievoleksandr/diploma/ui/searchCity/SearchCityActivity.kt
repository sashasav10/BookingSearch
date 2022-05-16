package com.savelievoleksandr.diploma.ui.searchCity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivitySearchCityBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.filter.FilterActivity

class SearchCityActivity :
    GeneralBinding<ActivitySearchCityBinding>(ActivitySearchCityBinding::inflate), OnLocationClick {
    private val viewModel by viewModels<SearchCityViewModel>()
    private var checkoutDate: String = ""
    private var checkinDate: String = ""
    private var room: Int = 1
    private var adult: Int = 2
    private var children: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_city)
        val arguments = intent.extras
        checkoutDate = arguments?.getString("checkoutDate").toString()
        checkinDate = arguments?.getString("checkinDate").toString()
        if (arguments != null) {
            room = arguments.getInt("room")
            adult = arguments.getInt("adult")
            children = arguments.getInt("children")
        }


        val locationAdapter = LocationAdapter(this as OnLocationClick)
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
                        recyclerView.adapter = locationAdapter
                        viewModel.locationResult.observe(this@SearchCityActivity) {
                            it?.let {
                                locationAdapter.submitList(it)
                            }
                        }
                    },
                    1000
                )
            }
        })
    }

    override fun onClick(
        locationId: Int,
        label: String,
        dest_type: String
    ) {
        val intent = Intent(this, FilterActivity::class.java)
        intent.putExtra("locationId", locationId).putExtra("dest_type", dest_type)
            .putExtra("label", label).putExtra("room", room)
            .putExtra("adult", adult).putExtra("children", children)
            .putExtra("checkoutDate", checkoutDate)
            .putExtra("checkinDate", checkinDate)
        startActivity(intent)
    }
}