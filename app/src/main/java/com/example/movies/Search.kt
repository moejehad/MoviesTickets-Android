package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.adapter.CategoriesAdapter
import com.example.movies.db.DatabaseHelper
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.activity_search.*
import kotlin.collections.ArrayList


class Search : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        back.setOnClickListener {
            onBackPressed()
        }

        val db = DatabaseHelper(this)
        val data = db.getAllMovies("desc",4)
        val finalResultSearch = ArrayList<Movie>()

        if(data.isEmpty()){
            noSearchData.setText("No movies added yet")
        }else {
            noSearchData.setText("")
            SearchRecycler.layoutManager = GridLayoutManager(this, 2)
            SearchRecycler.adapter = CategoriesAdapter(this, data)
        }


        searchInput.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.isNotEmpty()) {
                    finalResultSearch.clear()
                    var searchInput = p0.toString().toLowerCase()

                    for (i in data) {
                        if (searchInput in i.movieName!!.toLowerCase()) {
                            finalResultSearch.add(i)
                        }
                    }

                    SearchRecycler.adapter = CategoriesAdapter(this@Search, finalResultSearch)
                    Searchlatest.setText("Search Result")

                }else {
                    finalResultSearch.clear()
                    SearchRecycler.adapter = CategoriesAdapter(this@Search, data)
                    Searchlatest.text = "Latest Movies"
                }
                return true
            }


        });

    }

}