package com.example.movies.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.model.Booking
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.schedule_movies.view.*

class ScheduleAdapter (var activity: Activity , var data:ArrayList<Booking>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder>(){

    class ScheduleHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val schedule_image = itemView.schedule_image
        val schedule_title = itemView.schedule_title
        val schedule_date = itemView.schedule_date
        val schedule_tickets = itemView.schedule_tickets
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleHolder {
        val root = LayoutInflater.from(activity).inflate(R.layout.schedule_movies,parent,false)
        return ScheduleAdapter.ScheduleHolder(root)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ScheduleHolder, position: Int) {
        holder.schedule_image.setImageResource(R.drawable.spider)
        holder.schedule_title.text = data[position].filmName
        holder.schedule_date.text = data[position].filmDate
        holder.schedule_tickets.text = data[position].ticketsNumer
    }
}