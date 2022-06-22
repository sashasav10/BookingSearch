package com.savelievoleksandr.diploma.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.ui.auth.LoginActivity
import com.savelievoleksandr.diploma.ui.favorite.FavoriteActivity
import com.savelievoleksandr.diploma.ui.filter.FilterActivity
import com.savelievoleksandr.diploma.ui.searchCity.SearchCityActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_saved -> startActivity(Intent(this, FavoriteActivity::class.java))
            R.id.nav_account -> {
                if (FirebaseAuth.getInstance().currentUser?.uid != null) Toast.makeText(
                    this,
                    "You're already logged in",
                    Toast.LENGTH_SHORT
                ).show()
                else startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
                    .requestEmail()
                    .build()
                val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
                mGoogleSignInClient.signOut().addOnCompleteListener {
                    Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}