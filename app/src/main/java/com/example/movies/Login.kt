package com.example.movies

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movies.db.DatabaseHelper
import com.example.movies.model.User
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val db = DatabaseHelper(this)

        btn_login.setOnClickListener {

            if (phone.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "Fill All Fields Please", Toast.LENGTH_LONG).show()
            } else if (db.checkPhone(phone.text.toString())) {
                if (db.loginCheck(phone.text.toString(), password.text.toString())) {

                    val userInfo = db.getUser(phone.text.toString())

                    val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
                    val editor = sharedPref.edit()
                    editor.putInt("userID", userInfo.get(0).userID)
                    editor.putString("userName", userInfo.get(0).userName)
                    editor.putString("userEmail", userInfo.get(0).userEmail)
                    editor.putString("userPhone", userInfo.get(0).userPhone)
                    editor.putString("userPass", userInfo.get(0).userPassword)
                    editor.putString("userImage", userInfo.get(0).userImage)
                    val status = editor.commit()

                    if (status) {
                        val i = Intent(this, Home::class.java)
                        startActivity(i)
                        finish()
                    } else {
                        Toast.makeText(this, "Something get wrong", Toast.LENGTH_LONG).show()
                    }

                } else {
                    Toast.makeText(
                        this,
                        "Check Your Phone Number or Password Please",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } else {
                Toast.makeText(this, "This user is not exist", Toast.LENGTH_LONG).show()
            }


        }


        signUp_login.setOnClickListener {
            val i = Intent(this, SignUp::class.java)
            startActivity(i)
        }

    }


}