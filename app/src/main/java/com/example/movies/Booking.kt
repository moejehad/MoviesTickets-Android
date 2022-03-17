package com.example.movies

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movies.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

class Booking : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val db = DatabaseHelper(this)

        BookingBack.setOnClickListener {
            onBackPressed()
        }

        val bookName: String = intent.getStringExtra("bookName").toString()
        val bookImage: String = intent.getStringExtra("bookImage").toString()
        val bookDate: String = intent.getStringExtra("bookDate").toString()

        filmImage.setImageURI(Uri.parse(bookImage))
        filmTitle.setText(bookName)
        filmDateTime.setText(bookDate)

        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val usID = sharedPref.getInt("userID",0)
        val usName = sharedPref.getString("userName","").toString()

        film_booking_btn.setOnClickListener {
            if (numbersInput.text.isEmpty()){
                Toast.makeText(this,"Fill Number of Tickets Please",Toast.LENGTH_LONG).show()
            }else {
                val i = db.insertBooking(usID,usName,bookName,bookDate,numbersInput.text.toString())
                if (i) {
                    val thanks = Intent(this, thanks::class.java)
                    startActivity(thanks)
                    finish()
                } else {
                    Toast.makeText(this, "Booking Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}