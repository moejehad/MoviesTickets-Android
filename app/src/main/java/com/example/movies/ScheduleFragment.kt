package com.example.movies

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movies.adapter.ScheduleAdapter
import com.example.movies.db.DatabaseHelper
import com.example.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_schedule.*
import kotlinx.android.synthetic.main.fragment_schedule.view.*

class ScheduleFragment : Fragment() {

    private lateinit var root: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_schedule, container, false)

        val sharedPref = activity!!.getSharedPreferences("userData", MODE_PRIVATE)
        val usID = sharedPref.getInt("userID",0)

        val db = DatabaseHelper(activity!!)
        val data = db.getAllBooking(usID)

        if(data.isEmpty()){
            root.noData.setText("You haven't booked tickets yet")
        }else {
            root.noData.setText("")
            root.schedule_mov.setHasFixedSize(true)
            root.schedule_mov.layoutManager =
                LinearLayoutManager(activity!!, LinearLayoutManager.VERTICAL, false)
            root.schedule_mov.adapter = ScheduleAdapter(activity!!, data)
        }


        return root;
    }

}