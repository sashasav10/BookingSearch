package com.savelievoleksandr.diploma.ui.filter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.datepicker.MaterialDatePicker
import com.savelievoleksandr.diploma.databinding.ActivityFilterBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.hotelDeteiled.HotelActivity
import com.savelievoleksandr.diploma.ui.personFragment.PersonFragment
import com.savelievoleksandr.diploma.ui.searchCity.SearchCityActivity
import java.text.SimpleDateFormat


class FilterActivity : GeneralBinding<ActivityFilterBinding>(ActivityFilterBinding::inflate),
    PersonFragment.OnInputListener {
    private var room = 1
    private var adult = 2
    private var children = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val arguments = intent.extras
        val locationId = arguments?.getInt("locationId")!!.toInt()
        val dest_type = arguments.getString("dest_type").toString()
        val label = arguments.getString("label").toString()
        room = arguments.getInt("room")
        adult = arguments.getInt("adult")
        children = arguments.getInt("children")

        var checkoutDate = arguments.getString("checkoutDate")
        var checkinDate = arguments.getString("checkinDate")
        if (checkoutDate == "null" || checkinDate == "null") {
            checkoutDate = ""
            checkinDate = ""
        }
        val cityTextView: TextView = binding.cityTextView
        cityTextView.text = label
        val dateTextView: TextView = binding.dateTextView

        dateTextView.text = "$checkinDate  –  $checkoutDate"
        val filterTextView: TextView = binding.filterTextView
        filterTextView.text = "$room room | $adult adilts | $children children"
        val backBtn: ImageButton = binding.backBtn
        val filterBack: TextView = binding.filterBack
        val clearBtn: Button = binding.clearBtn

        val inputCityBtn: View = binding.inputCityBtn
        val dateField: View = binding.dateField
        val personsField: View = binding.filterField
        val searchBtn: Button = binding.searchBtn

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
            dateRangePicker.show(supportFragmentManager, "tag")
            val format = SimpleDateFormat("yyyy-MM-dd")
            dateRangePicker.addOnPositiveButtonClickListener {
                checkoutDate = format.format(it.second)
                checkinDate = format.format(it.first)
                dateTextView.text = "$checkinDate  –  $checkoutDate"
            }
        }
        personsField.setOnClickListener {
            val fragmentManager = supportFragmentManager
            fragmentManager.beginTransaction()
            val fragment = PersonFragment()
            fragment.show(fragmentManager, "Choose number of pearson")
        }
        backBtn.setOnClickListener { this.finish() }
        filterBack.setOnClickListener { this.finish() }
        clearBtn.setOnClickListener {
            dateTextView.text = "Select dates"
            checkinDate = ""
            checkoutDate = ""
            room = 0
            adult = 0
            children = 0
            filterTextView.text = "$room room | $adult adilts | $children children"
        }

        searchBtn.setOnClickListener {
            if (room == 0 || adult == 0 || checkinDate == "" || checkoutDate == "" || locationId == 0
            ) {
                Toast.makeText(this, "Please enter all fields before searching", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, HotelActivity::class.java)
                intent.putExtra("locationId", locationId).putExtra("label", label)
                    .putExtra("dest_type", dest_type).putExtra("checkoutDate", checkoutDate)
                    .putExtra("checkinDate", checkinDate).putExtra("room", room)
                    .putExtra("adult", adult).putExtra("children", children)
                startActivity(intent)
            }
        }
    }

    override fun sendInput(room: Int, adult: Int, children: Int) {
        this.room = room
        this.adult = adult
        this.children = children
        val filterTextView: TextView = binding.filterTextView
        filterTextView.text = "${this.room} room | ${this.adult} adilts | ${this.children} children"
    }
}