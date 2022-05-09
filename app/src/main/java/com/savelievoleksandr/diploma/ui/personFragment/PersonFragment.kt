package com.savelievoleksandr.diploma.ui.personFragment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.savelievoleksandr.diploma.databinding.FilterFragmentBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.filter.FilterActivity


class PersonFragment : GeneralBinding<FilterFragmentBinding>(FilterFragmentBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        var room = 1
        var adult = 2
        var children = 0
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val arguments = intent.extras
        val locationId = arguments?.getInt("locationId")
        val destType = arguments?.getString("dest_type").toString()
        val label = arguments?.getString("label").toString()
        val checkoutDate = arguments?.getString("checkoutDate").toString()
        val checkinDate = arguments?.getString("checkinDate").toString()
        val addRo: Button = binding.addRo
        val minusRo: Button = binding.minusRo
        val addAd: Button = binding.addAd
        val minusAd: Button = binding.minusAd
        val addCh: Button = binding.addCh
        val minusCh: Button = binding.minusCh
        val roomCount: TextView = binding.roomCount
        val adultCount: TextView = binding.adultCount
        val childCount: TextView = binding.childCount

        val confirmBtn: TextView = binding.confirmBtn

        addRo.setOnClickListener {
            room++
            roomCount.text = room.toString()
        }
        minusRo.setOnClickListener {
            room--
            roomCount.text = room.toString()
        }
        addAd.setOnClickListener {
            adult++
            adultCount.text = adult.toString()
        }
        minusAd.setOnClickListener {
            adult--
            adultCount.text = adult.toString()
        }
        addCh.setOnClickListener {
            children++
            childCount.text = children.toString()
        }
        minusCh.setOnClickListener {
            children--
            childCount.text = children.toString()
        }
        confirmBtn.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            intent.putExtra("locationId", locationId).putExtra("dest_type", destType)
                .putExtra("label", label).putExtra("room", room)
                .putExtra("adult", adult).putExtra("children", children)
                .putExtra("checkoutDate", checkoutDate)
                .putExtra("checkinDate", checkinDate)
            this.finish()
            startActivity(intent)
        }
    }
}