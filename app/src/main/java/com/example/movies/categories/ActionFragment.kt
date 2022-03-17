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
import kotlinx.android.synthetic.main.fragment_action.*
import kotlinx.android.synthetic.main.fragment_action.view.*


class ActionFragment : Fragment() {

    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_action, container, false)

        val db = DatabaseHelper(activity!!)
        val data = db.getCategoriesMovies("Action")

        if(data.isEmpty()){
            root.noData.setText("No movies added yet")
        }else {
            root.noData.setText("")
            root.categoriesRecy.setHasFixedSize(true)
            root.categoriesRecy.layoutManager = GridLayoutManager(activity!!, 2)
            root.categoriesRecy.adapter = CategoriesAdapter(activity!!, data)
        }

        return root;
    }

}