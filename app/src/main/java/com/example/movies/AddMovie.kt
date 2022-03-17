package com.example.movies

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.core.view.get
import com.example.movies.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_add_movie.back
import java.util.*

class AddMovie : AppCompatActivity() {

    var ImageUri: String ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        val db = DatabaseHelper(this)

        var date = ""
        var time = ""

        MoDate.setOnClickListener {

            val currentDate = Calendar.getInstance()
            val day = currentDate.get(Calendar.DAY_OF_MONTH)
            val month = currentDate.get(Calendar.MONTH)
            val year = currentDate.get(Calendar.YEAR)

            val picker = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { datePicker, y, m, d ->
                    date = "$y - ${m+1} - $d"
                    MoDate.setText("$y - ${m+1} - $d")
                },year,month,day)

            picker.show()
        }

        MoTime.setOnClickListener {
            val currentTime = Calendar.getInstance()
            val hour = currentTime.get(Calendar.HOUR_OF_DAY)
            val minute = currentTime.get(Calendar.MINUTE)

            val picker = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { timePicker, h, m ->
                    time = "$h : $m"
                    MoTime.setText("$h : $m")
                },
                hour,minute,false
            )
            picker.show()
        }

        MovieImg.setOnClickListener {
            if (Build.VERSION.SDK_INT >= 23) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                { requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 20)
                    return@setOnClickListener
                } else {
                    val gal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(gal,100)
                }
            }else {
                val gal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(gal, 100)
            }
        }

        addCat.setOnClickListener {
            if (MoName.text.isEmpty() || MoDesc.text.isEmpty() || MoDate.text.isEmpty() || MoTime.text.isEmpty() || MoDura.text.isEmpty()) {
                Toast.makeText(this, "Fill Fields Please", Toast.LENGTH_LONG).show()
            } else {

                val add = db.insertMovie(
                    MoName.text.toString(),
                    MoDesc.text.toString(),
                    date.toString(),
                    time.toString(),
                    MoDura.text.toString(),
                    MoCat.getSelectedItem().toString(),
                    ImageUri.toString()
                )
                if (add) {
                    Toast.makeText(this, "Added Successfully", Toast.LENGTH_LONG).show()
                    val i = Intent(this,Home::class.java)
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(this, "Added Failed", Toast.LENGTH_LONG).show()
                }

            }
        }


        back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {

            val takeflag : Int = intent.flags
            takeflag.and(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            takeflag.or(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            contentResolver.takePersistableUriPermission(data?.data!!,takeflag)

            Log.e("a", data!!.data.toString())
            MovieImg.setImageURI(data!!.data)
            ImageUri = data.data.toString()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            20 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val gal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(gal, 100)
                } else {
                    finish()
                }
            }
        }
    }

}