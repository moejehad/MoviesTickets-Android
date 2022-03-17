package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movies.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val db = DatabaseHelper(this)

        btn_signUp.setOnClickListener {
            if (signUp_username.text.isEmpty() || signUp_password.text.isEmpty() || signUp_email.text.isEmpty() || signUp_phone.text.isEmpty()) {

                Toast.makeText(this, "Fill All Fields Please", Toast.LENGTH_LONG).show()

            } else {

                if (db.checkPhone(signUp_phone.text.toString())) {
                    Toast.makeText(this, "This user is already registered", Toast.LENGTH_LONG)
                        .show()
                } else {
                    val i = db.insertUser(
                        signUp_username.text.toString(), signUp_password.text.toString(),
                        signUp_email.text.toString(), signUp_phone.text.toString(),""
                    )
                    if (i) {
                        Toast.makeText(this, "You Register Successfully", Toast.LENGTH_LONG).show()
                        val lg = Intent(this, Login::class.java)
                        startActivity(lg)
                        finish()
                    } else {
                        Toast.makeText(this, "Register Failed", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        login_signUp.setOnClickListener {
            onBackPressed()
        }


    }
}