package com.savelievoleksandr.diploma.ui.searchCity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.savelievoleksandr.diploma.R
import com.savelievoleksandr.diploma.databinding.ActivitySearchCityBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding
import com.savelievoleksandr.diploma.ui.auth.LoginActivity
import com.savelievoleksandr.diploma.ui.favorite.FavoriteActivity
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