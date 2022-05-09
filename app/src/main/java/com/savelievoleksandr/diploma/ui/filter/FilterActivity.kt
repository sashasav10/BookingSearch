package com.savelievoleksandr.diploma.ui.filter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivityFilterBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.hotelDeteiled.HotelActivity
import com.savelievoleksandr.diploma.ui.personFragment.PersonFragment
import com.savelievoleksandr.diploma.ui.searchCity.SearchCityActivity
import java.text.SimpleDateFormat

class FilterActivity : GeneralBinding<ActivityFilterBinding>(ActivityFilterBinding::inflate) {
    private val viewModel by viewModels<FilterViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        val arguments = intent.extras
        val locationId = arguments?.getInt("locationId")!!.toInt()
        val dest_type = arguments.getString("dest_type").toString()
        val label = arguments.getString("label").toString()
        var room = arguments.getInt("room")
        var adult = arguments.getInt("adult")
        var children = arguments.getInt("children")
        var checkoutDate = arguments.getString("checkoutDate").toString()
        var checkinDate = arguments.getString("checkinDate").toString()

        val cityTextView: TextView = findViewById(R.id.cityTextView)
        cityTextView.text = label
        val dateTextView: TextView = findViewById(R.id.dateTextView)
        dateTextView.text = "$checkinDate  –  $checkoutDate"
        val filterTextView: TextView = findViewById(R.id.filterTextView)
        filterTextView.text = "$room room | $adult adilts | $children children"
        val backBtn: ImageButton = findViewById(R.id.backBtn)
        val filterBack: TextView = findViewById(R.id.filterBack)
        val clearBtn: Button = findViewById(R.id.clearBtn)

        val inputCityBtn: View = findViewById(R.id.inputCityBtn)
        val dateField: View = findViewById(R.id.dateField)
        val personsField: View = findViewById(R.id.filterField)
        val searchBtn: Button = findViewById(R.id.searchBtn)

        inputCityBtn.setOnClickListener {
            val intent = Intent(this, SearchCityActivity::class.java)
            intent.putExtra("checkoutDate", checkoutDate)
                .putExtra("checkinDate", checkinDate).putExtra("room", room)
                .putExtra("adult", adult).putExtra("children", children)
            this.finish()
            startActivity(intent)
        }
        dateField.setOnClickListener {
            val dateRangePicker =
                MaterialDatePicker.Builder.dateRangePicker()
                    .setTitleText("Select dates")
                    //.setTheme(R.style.Theme_App)
                    .build()
            dateRangePicker.show(supportFragmentManager, "tag");
            val format = SimpleDateFormat("yyyy-MM-dd")
            dateRangePicker.addOnPositiveButtonClickListener {
                checkoutDate = format.format(it.second)
                checkinDate = format.format(it.first)
                dateTextView.text = "$checkinDate  –  $checkoutDate"
            }
        }
        personsField.setOnClickListener {
            val intent = Intent(this, PersonFragment::class.java)
            intent.putExtra("locationId", locationId).putExtra("label", label)
                .putExtra("dest_type", dest_type).putExtra("checkoutDate", checkoutDate)
                .putExtra("checkinDate", checkinDate)
            this.finish()
            startActivity(intent)
        }
        backBtn.setOnClickListener { this.finish() }
        filterBack.setOnClickListener { this.finish() }
        clearBtn.setOnClickListener {
            dateTextView.text = "Select dates"
            checkinDate=""
            checkoutDate=""
            room = 0
            adult = 0
            children = 0
            filterTextView.text = "$room room | $adult adilts | $children children"
        }

        searchBtn.setOnClickListener {
            val intent = Intent(this, HotelActivity::class.java)
            intent.putExtra("locationId", locationId).putExtra("label", label)
                .putExtra("dest_type", dest_type).putExtra("checkoutDate", checkoutDate)
                .putExtra("checkinDate", checkinDate).putExtra("room", room)
                .putExtra("adult", adult).putExtra("children", children)
            startActivity(intent)
        }
    }
}