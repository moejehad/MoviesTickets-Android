package com.example.movies.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.MovieDetails
import com.example.movies.R
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.movie.view.*

class MovieAdapter(var activity: Activity, var data: ArrayList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thub = itemView.thubmanil
        val title = itemView.movieTitle
        val duration = itemView.time
        val itemMovie = itemView.itemMovie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.movie, parent, false)
        return MyViewHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = data[position].movieName
        holder.duration.text = data[position].duration
        holder.thub.setImageURI(Uri.parse(data[position].thub))

        holder.itemMovie.setOnClickListener {
            val i = Intent(activity, MovieDetails::class.java)
            i.putExtra("HomeData",data[position])
            activity.startActivity(i)
        }
    }


}