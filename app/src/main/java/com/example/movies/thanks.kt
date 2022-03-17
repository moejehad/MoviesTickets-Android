package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_thanks.*

class thanks : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thanks)

        backHome.setOnClickListener {
            val thanks = Intent(this, Home::class.java)
            startActivity(thanks)
            finish()
        }
    }
}