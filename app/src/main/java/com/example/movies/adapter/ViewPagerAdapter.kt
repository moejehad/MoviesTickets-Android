package com.example.movies.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.movies.HomeFragment
import com.example.movies.ProfileFragment
import com.example.movies.ScheduleFragment
import com.example.movies.categories.*
import com.example.movies.db.DatabaseHelper

private val TAB_TITLES =
    arrayOf(
        "Action",
        "Comedies",
        "Family",
        "Romance",
        "Anime"
    )

class ViewPagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                fragment = ActionFragment()
            }
            1 -> {
                fragment = ComediesFragment()
            }
            2 -> {
                fragment = FamilyFragment()
            }
            3 -> {
                fragment = RomanceFragment()
            }
            4 -> {
                fragment = AnimeFragment()
            }
            else -> {
                fragment = ActionFragment()
            }
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }


}