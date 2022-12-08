package com.example.healthscoregoal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExerciseAdapter (private val context: Context, private val exerciseLists: List<Exercises>):
    RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var exDay: TextView
        var exName: TextView
        var exLength: TextView
        var bCals: TextView

        init {
            exDay = itemView.findViewById(R.id.enterdEXDayTV)
            exName = itemView.findViewById(R.id.enterdWorkType)
            exLength = itemView.findViewById(R.id.enterdWLengthTV)
            bCals = itemView.findViewById(R.id.enterdRepMiles)

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val wLView = inflater.inflate(R.layout.exercise_item, parent, false)

        return ViewHolder(wLView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitList2 = exerciseLists[position]

        holder.exDay.text = bitList2.exDay
        holder.exName.text = bitList2.exName
        holder.exLength.text = bitList2.exTime.toString()
        holder.bCals.text = bitList2.burntCals.toString()
    }
    override fun getItemCount() = exerciseLists.size
}