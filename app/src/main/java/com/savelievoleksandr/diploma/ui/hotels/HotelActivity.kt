package com.savelievoleksandr.diploma.ui.hotels

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivityHotelsBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.auth.LoginActivity
import com.savelievoleksandr.diploma.ui.favorite.FavoriteActivity
import com.savelievoleksandr.diploma.ui.hotelDetailed.DetailedActivity
import com.savelievoleksandr.diploma.ui.main.MainActivity

class HotelActivity :
    GeneralBinding<ActivityHotelsBinding>(ActivityHotelsBinding::inflate), OnHotelClick {
    private val viewModel by viewModels<HotelViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotels)
        val arguments = intent.extras
        val dest_id = arguments?.getInt("locationId")!!.toInt()
        val dest_type = arguments.getString("dest_type").toString()
        val label = arguments.getString("label").toString()
        val checkoutDate = arguments.getString("checkoutDate").toString()
        val checkinDate = arguments.getString("checkinDate").toString()
        val room = arguments.getInt("room")
        val adult = arguments.getInt("adult")
        val children = arguments.getInt("children")
        Log.i(
            "SASHA",
            "$checkoutDate, $dest_id, $dest_type, $adult, UAH, $checkinDate, $room, children $children"
        )

        val backBtn2: ImageButton = findViewById(R.id.backBtn2)
        val cityTV: TextView = findViewById(R.id.cityTV)
        cityTV.text = label.split(",")[0]
        backBtn2.setOnClickListener { this.finish() }
        cityTV.setOnClickListener { this.finish() }
        val recyclerView: RecyclerView = findViewById(R.id.hotelRecyclerView)
        viewModel.getHotel(checkoutDate, dest_id, dest_type, adult, checkinDate, room, children)
        viewModel.hotelResult.observe(this) {
            recyclerView.adapter = HotelAdapter(this@HotelActivity, it)
        }
    }

    override fun onClick(
        hotel_id: Int,
        hotel_name: String,
        city_name: String,
        review_score: Double,
        review_score_word: String,
        address: String,
        distance_to_cc: Double,
        is_free_cancellable: Byte,
        hotel_include_breakfast: Byte,
        min_total_price: Double,
        url: String,
        max_photo_url: String
    ) {
        val intent = Intent(this, DetailedActivity::class.java)
        intent.putExtra("hotel_id", hotel_id).putExtra("hotel_name", hotel_name)
            .putExtra("city_name", city_name)
            .putExtra("review_score", review_score).putExtra("review_score_word", review_score_word)
            .putExtra("address", address).putExtra("distance_to_cc", distance_to_cc)
            .putExtra("is_free_cancellable", is_free_cancellable)
            .putExtra("hotel_include_breakfast", hotel_include_breakfast)
            .putExtra("min_total_price", min_total_price)
            .putExtra("url", url).putExtra("max_photo_url", max_photo_url)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        if (FirebaseAuth.getInstance().currentUser?.uid != null) {
            menu.findItem(R.id.nav_account).setIcon(R.drawable.ic_baseline_exit_to_app_24)
        }
        else menu.findItem(R.id.nav_account).setIcon(R.drawable.ic_account)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                intent.putExtra("EXIT", true)
                startActivity(intent)
            }
            R.id.nav_saved -> startActivity(Intent(this, FavoriteActivity::class.java))
            R.id.nav_account -> {
                if (FirebaseAuth.getInstance().currentUser?.uid != null) {
                    FirebaseAuth.getInstance().signOut()
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
                        .requestEmail()
                        .build()
                    val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
                    mGoogleSignInClient.signOut().addOnCompleteListener {
                        Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                    }
                    item.setIcon(R.drawable.ic_account)
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                    Handler(Looper.getMainLooper()).postDelayed(
                        {item.setIcon(R.drawable.ic_baseline_exit_to_app_24)},1000)
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
}