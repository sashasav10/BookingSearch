package com.savelievoleksandr.diploma.ui.auth

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.savelievoleksandr.diploma.R

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val textEmail: TextView = findViewById(R.id.text_email)
        val editTextPassword: TextView = findViewById(R.id.edit_text_password)
        val registerBtn: Button = findViewById(R.id.button_register)
        registerBtn.setOnClickListener {
            val email = textEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (email.isEmpty()) {
                textEmail.error = "Email Required"
                textEmail.requestFocus()
                return@setOnClickListener
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                textEmail.error = "Valid Email Required"
                textEmail.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty() || password.length < 6) {
                editTextPassword.error = "6 char password required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val firebaseUser: FirebaseUser = task.result!!.user!!
                            Toast.makeText(this, "Successful registration", Toast.LENGTH_SHORT)
                                .show()
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}