package com.savelievoleksandr.diploma.ui.hotelDetailed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.data.Favorite
import com.savelievoleksandr.diploma.databinding.ActivityHotelDetailedBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.auth.LoginActivity
import com.savelievoleksandr.diploma.ui.favorite.FavoriteActivity
import com.savelievoleksandr.diploma.ui.main.MainActivity


class DetailedActivity :
    GeneralBinding<ActivityHotelDetailedBinding>(ActivityHotelDetailedBinding::inflate) {
    private val viewModel by viewModels<DetailedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val arguments = intent.extras
        val hotel_id = arguments?.getInt("hotel_id")!!.toInt()
        val hotel_name = arguments.getString("hotel_name").toString()
        val city_name = arguments.getString("city_name").toString()
        val review_score = arguments.getDouble("review_score")
        val review_score_word = arguments.getString("review_score_word").toString()
        val addres = arguments.getString("address").toString()
        val distance_to_cc = arguments.getDouble("distance_to_cc")
        val is_free_cancellable = arguments.getByte("is_free_cancellable")
        val hotel_include_breakfast = arguments.getByte("hotel_include_breakfast")
        val min_total_price = arguments.getDouble("min_total_price")
        val url = arguments.getString("url").toString()
        val max_photo_url = arguments.getString("max_photo_url").toString()
        Log.i("SASHA", "hotel_id in activity $hotel_id")

        val hotelName: TextView = binding.hotelName
        val score: TextView = binding.score
        val scoreWord: TextView = binding.scoreWord
        val address: TextView = binding.address
        val distance: TextView = binding.distance
        val cancellation: TextView = binding.cancellation
        val breakfast: TextView = binding.breakfast
        val price: TextView = binding.price
        val bookBtn: Button = binding.bookBtn
        val addToFavBtn: ImageButton = binding.addToFavBtn
        hotelName.text = hotel_name
        score.text = review_score.toString()
        scoreWord.text = review_score_word
        address.text = addres
        distance.text = distance_to_cc.toString()
        if (is_free_cancellable < 1) {
            cancellation.text = "Cancellation is not free"
        } else {
            cancellation.text = "Free cancellation"
        }
        if (hotel_include_breakfast < 1) {
            breakfast.text = "Breakfast is not included"
        } else {
            breakfast.text = "Breakfast included"
        }
        price.text = min_total_price.toString()

        val recyclerView: RecyclerView = binding.photoRecyclerView
        viewModel.getPhoto(hotel_id)
        viewModel.photoResult.observe(this) {
            recyclerView.adapter = DetailedAdapter(it)
        }

        val backBtn3: ImageButton = binding.backBtn3
        backBtn3.setOnClickListener { this.finish() }
        bookBtn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
        addToFavBtn.setOnClickListener {
            val uid = FirebaseAuth.getInstance().currentUser?.uid
            if (uid.isNullOrEmpty()) Toast.makeText(
                this,
                "Please log in to save",
                Toast.LENGTH_SHORT
            ).show()
            else {
                addToFavBtn.setImageResource(R.drawable.ic_favorite)
                val fav = Favorite(hotel_id, hotel_name, city_name, addres, max_photo_url)
                val database =
                    Firebase.database("https://diploma-hotel-booking-default-rtdb.europe-west1.firebasedatabase.app/")
                val myRef = database.getReference("users")
                myRef.child(uid).child(hotel_id.toString()).setValue(fav)
            }
        }
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