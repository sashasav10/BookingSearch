package com.savelievoleksandr.diploma.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.savelievoleksandr.diploma.data.Favorite
import com.savelievoleksandr.diploma.databinding.ActivityFavoriteBinding
import com.savelievoleksandr.diploma.ui.GeneralBinding

class FavoriteActivity : GeneralBinding<ActivityFavoriteBinding>(ActivityFavoriteBinding::inflate) {
    val favList = arrayListOf<Favorite>()
    var adapter = FavoriteAdapter(favList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val recyclerView: RecyclerView = binding.FavRecyclerView
        val database =
            Firebase.database("https://diploma-hotel-booking-default-rtdb.europe-west1.firebasedatabase.app/")
        val myRef = database.getReference("users").child(uid.toString())

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("FirebaseError", "loadPost:onCancelled", databaseError.toException())
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (h in dataSnapshot.children) {
                    val fav = h.getValue(Favorite::class.java)
                    favList.add(fav!!)
                }
                adapter = FavoriteAdapter(favList)
                recyclerView.adapter = adapter
            }
        })

    }
}