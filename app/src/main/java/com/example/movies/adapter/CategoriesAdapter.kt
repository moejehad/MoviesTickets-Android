package com.example.movies.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.MovieDetails
import com.example.movies.R
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.categories.view.*

class CategoriesAdapter (var activity: Activity , var data:ArrayList<Movie>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>(){

    class CategoriesHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val categoriesImage = itemView.categoriesImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.categories,parent,false)
        return CategoriesAdapter.CategoriesHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CategoriesAdapter.CategoriesHolder, position: Int) {
        holder.categoriesImage.setImageURI(Uri.parse(data[position].thub))
        holder.categoriesImage.setOnClickListener {
            val i = Intent(activity, MovieDetails::class.java)
            i.putExtra("HomeData",data[position])
            activity.startActivity(i)
        }
    }

}