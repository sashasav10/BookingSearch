package com.savelievoleksandr.diploma.ui.personFragment

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.FilterFragmentBinding


class PersonFragment : DialogFragment(R.layout.filter_fragment){
    private lateinit var binding: FilterFragmentBinding

    interface OnInputListener {
        fun sendInput(room:Int,adult:Int,children:Int)
    }
    var listener: OnInputListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var window = dialog?.window
        window?.setGravity(Gravity.BOTTOM)
        return inflater.inflate(R.layout.filter_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FilterFragmentBinding.inflate(layoutInflater)
        super.onViewCreated(view, savedInstanceState)
        var room = 1
        var adult = 2
        var children = 0
        super.onCreate(savedInstanceState)
//        val locationId = arguments?.getInt("locationId")
//        val destType = arguments?.getString("dest_type").toString()
//        val label = arguments?.getString("label").toString()
//        val checkoutDate: String = arguments?.getString("checkoutDate").toString()
//        val checkinDate = arguments?.getString("checkinDate").toString()
        val addRo: Button = view.findViewById(R.id.addRo)
        val minusRo: Button = view.findViewById(R.id.minusRo)
        val addAd: Button = view.findViewById(R.id.addAd)
        val minusAd: Button = view.findViewById(R.id.minusAd)
        val addCh: Button = view.findViewById(R.id.addCh)
        val minusCh: Button = view.findViewById(R.id.minusCh)
        val roomCount: TextView = view.findViewById(R.id.roomCount)
        val adultCount: TextView = view.findViewById(R.id.adultCount)
        val childCount: TextView = view.findViewById(R.id.childCount)

        val confirmBtn: TextView = view.findViewById(R.id.confirmBtn)

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
            listener!!.sendInput(room, adult, children)
            dismiss()
//            val intent = Intent(activity, FilterActivity::class.java)
//            intent.putExtra("locationId", locationId).putExtra("dest_type", destType)
//                .putExtra("label", label).putExtra("room", room)
//                .putExtra("adult", adult).putExtra("children", children)
//                .putExtra("checkoutDate", checkoutDate)
//                .putExtra("checkinDate", checkinDate)
//            startActivity(intent)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener=context as OnInputListener
    }
}