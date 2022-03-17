package com.example.movies

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movies.db.DatabaseHelper
import com.example.movies.model.User
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val HomeFragment = HomeFragment()
        val scheduleFragment = ScheduleFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(HomeFragment)

        bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tvHome -> setCurrentFragment(HomeFragment)
                R.id.calendar -> setCurrentFragment(scheduleFragment)
                R.id.profile -> setCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_layout, fragment)
            commit()
        }

    }
}