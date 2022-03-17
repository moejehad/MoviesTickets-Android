package com.example.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.adapter.MovieAdapter
import com.example.movies.adapter.ViewPagerAdapter
import com.example.movies.db.DatabaseHelper
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_schedule.*

class HomeFragment : Fragment() {


    private lateinit var root: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_home, container, false)

        val db = DatabaseHelper(activity!!)
        val data = db.getAllMovies("asc",6)

        if(data.isEmpty()){
            root.noData.setText("No movies added yet")
        }else {
            root.noData.setText("")
            root.movies.setHasFixedSize(true)
            root.movies.layoutManager =
                LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)
            val moviesAdapter = MovieAdapter(activity!!, data)
            root.movies.adapter = moviesAdapter
        }

        setUpTabs()

        root.search.setOnClickListener {
            val i = Intent(activity!!, Search::class.java)
            startActivity(i)
        }

        return root;
    }



    private fun setUpTabs() {

        root.viewPager.adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        root.tabs.setupWithViewPager(root.viewPager)

    }

}
