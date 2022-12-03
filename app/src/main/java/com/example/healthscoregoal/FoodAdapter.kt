package com.example.healthscoregoal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter (private val context: Context, private val foodLists: List<Foods>):
    RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fNameTv: TextView
        var sugTv: TextView
        var carbTv: TextView
        var calTv: TextView
        init {
            fNameTv = itemView.findViewById(R.id.enterdFoodNameTV)
            sugTv = itemView.findViewById(R.id.enterdFoodSugarTV)
            carbTv = itemView.findViewById(R.id.enterdFoodCarbTV)
            calTv = itemView.findViewById(R.id.enterdFoodCaloriesTV)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val wLView = inflater.inflate(R.layout.food_item, parent, false)

        return ViewHolder(wLView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitList1 = foodLists[position]

        holder.fNameTv.text = bitList1.foodName
        holder.sugTv.text = bitList1.sugars.toString()
        holder.carbTv.text = bitList1.carbs.toString()
        holder.calTv.text = bitList1.calories.toString()
    }
    override fun getItemCount() = foodLists.size
}