package com.example.movies

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.movies.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : AppCompatActivity() {

    var ImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val db = DatabaseHelper(this)
        val sharedPref = getSharedPreferences("userData", MODE_PRIVATE)
        val editor = sharedPref.edit()
        val editID = sharedPref.getInt("userID", 0)

        edit_username.setText(sharedPref.getString("userName", ""))
        edit_email.setText(sharedPref.getString("userEmail", ""))
        edit_phone.setText(sharedPref.getString("userPhone", ""))
        edit_password.setText(sharedPref.getString("userPass", ""))


        changePhoto.setOnClickListener {
            val gal = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(gal,100)
        }

        btn_edit.setOnClickListener {

            val editName = edit_username.text.toString()
            val editEmail = edit_email.text.toString()
            val editPhone = edit_phone.text.toString()
            val editPass = edit_password.text.toString()

            if (edit_username.text.isEmpty() || edit_email.text.isEmpty() || edit_phone.text.isEmpty() || edit_password.text.isEmpty()) {
                Toast.makeText(this, "The Inputs should be filled", Toast.LENGTH_LONG).show()
            } else {

                val edit = db.updateUser(editID, editName, editPass , editEmail, editPhone , ImageUri.toString())
                if (edit) {
                    editor.putInt("userID", editID)
                    editor.putString("userName", editName)
                    editor.putString("userEmail", editEmail)
                    editor.putString("userPhone", editPhone)
                    editor.putString("userPass", editPass)
                    editor.putString("userImage", ImageUri.toString())
                    editor.commit()

                    Toast.makeText(this, "Data Updated Successfully", Toast.LENGTH_LONG).show()

                    val i = Intent(this,Login::class.java)
                    startActivity(i)
                    finish()
                } else {
                    Toast.makeText(this, "Check the inputs please", Toast.LENGTH_LONG).show()
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
            Log.e("a", data!!.data.toString())
            Edit_Photo.setImageURI(data!!.data)
            ImageUri = data.data
        }
    }


}