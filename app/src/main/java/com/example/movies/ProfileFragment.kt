package com.example.movies

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {

    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPref = activity!!.getSharedPreferences("userData", AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPref.edit()

        root.name_profile.text = sharedPref.getString("userName","")
        root.email_profile.text = sharedPref.getString("userEmail","")
        val photo  = sharedPref.getString("userImage","")

        /*
        if (sharedPref.getString("userImage","")!!.isNotEmpty()){
            root.photo.setImageURI(Uri.parse(photo))
        }
         */

        root.addMovie.setOnClickListener {
            val i = Intent(activity!!,AddMovie::class.java)
            startActivity(i)
        }
        root.edit.setOnClickListener{
            val i = Intent(activity!!,EditProfile::class.java)
            startActivity(i)
        }
        root.logout.setOnClickListener {
            editor.clear()
            val i = Intent(activity!!,Login::class.java)
            startActivity(i)
            activity!!.finish()
        }

        return root;
    }


}