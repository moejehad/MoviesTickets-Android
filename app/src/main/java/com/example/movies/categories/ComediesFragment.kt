package com.example.movies.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movies.R
import com.example.movies.adapter.CategoriesAdapter
import com.example.movies.db.DatabaseHelper
import kotlinx.android.synthetic.main.fragment_anime.*
import kotlinx.android.synthetic.main.fragment_comedies.*
import kotlinx.android.synthetic.main.fragment_comedies.noData
import kotlinx.android.synthetic.main.fragment_comedies.view.*


class ComediesFragment : Fragment() {

    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_comedies, container, false)

        val db = DatabaseHelper(activity!!)
        val data = db.getCategoriesMovies("Comedies")

        if(data.isEmpty()){
            root.noData.setText("No movies added yet")
        }else {
            root.noData.setText("")
            root.comRecy.setHasFixedSize(true)
            root.comRecy.layoutManager = GridLayoutManager(activity!!, 2)
            root.comRecy.adapter = CategoriesAdapter(activity!!, data)
        }


        return root;
    }

}