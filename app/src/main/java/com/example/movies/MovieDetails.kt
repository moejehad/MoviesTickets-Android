package com.example.movies

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.movies.db.DatabaseHelper
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        val info = intent.getParcelableExtra<Movie>("HomeData")

        val filmID: Int = info?.ID ?: 0
        val filmName: String = info?.movieName.toString()
        val filmImage: String = info?.thub.toString()
        val filmDuration: String = info?.duration.toString()
        val filmTime: String = info?.time.toString() +" | "+ info?.date.toString()
        val description: String = info?.description.toString()
        val catName: String = info?.categories.toString()

        MovieImage.setImageURI(Uri.parse(filmImage))
        detalisTitle.text = filmName
        time.text = filmDuration
        DetalisYear.text = filmTime
        DetalisDesc.text = description
        category.text = catName

        booking_btn.setOnClickListener {
            val i = Intent(this,Booking::class.java)
            i.putExtra("bookName",filmName)
            i.putExtra("bookImage",filmImage)
            i.putExtra("bookDate", filmTime)
            startActivity(i)
        }

        deleteMovie.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Delete Movie")
            alertDialog.setMessage("Are you sure want to delete this movie ?")

            alertDialog.setPositiveButton("Yes"){ _,_ ->
                val db = DatabaseHelper(this)
                val d = db.deleteMovie(filmID)
                if (d){
                    Toast.makeText(this,"Movie Deleted Successfully",Toast.LENGTH_LONG).show()
                    val i = Intent(this,Home::class.java)
                    startActivity(i)
                    finish()
                }else {
                    Toast.makeText(this,"Movie Delete Failed",Toast.LENGTH_LONG).show()
                }
            }

            alertDialog.setNegativeButton("No"){ d,_ ->
                d.dismiss()
            }

            alertDialog.create().show()
        }

    }
}