package com.example.healthscoregoal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter(private val context: Context, private val sleepLists: List<Fitness>):
    RecyclerView.Adapter<SleepAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var dayTV: TextView
        var sleepTV: TextView
        init {
            dayTV = itemView.findViewById(R.id.enterdDayTV)
            sleepTV = itemView.findViewById(R.id.enterdHoursSlept)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val wLView = inflater.inflate(R.layout.sleep_item, parent, false)

        return ViewHolder(wLView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitList = sleepLists[position]

        holder.dayTV.text = bitList.dayText
        holder.sleepTV.text = bitList.hoursSlept.toString()
    }
    override fun getItemCount() = sleepLists.size
}