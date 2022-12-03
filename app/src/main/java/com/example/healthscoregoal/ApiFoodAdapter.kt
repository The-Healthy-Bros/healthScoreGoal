package com.example.healthscoregoal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ApiFoodAdapter (private val apiLists: List<ApiTest>):
    RecyclerView.Adapter<ApiFoodAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var apiTitle: TextView
        var apiName: TextView
        var apiAmount: TextView
        var apiUnit: TextView
        var apiImage: ImageView
        init {
            apiTitle = itemView.findViewById(R.id.titleTV)
            apiName = itemView.findViewById(R.id.apiNameTV)
            apiAmount = itemView.findViewById(R.id.apiAmountTV)
            apiUnit = itemView.findViewById(R.id.apiUnitTV)
            apiImage = itemView.findViewById(R.id.imageIV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val wLView = inflater.inflate(R.layout.food_item, parent, false)

        return ViewHolder(wLView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val apiList = apiLists[position]

        holder.apiTitle.text = apiList.Title
        holder.apiName.text = apiList.Nutrition?.nutrients?.Name
        holder.apiAmount.text = apiList.Nutrition?.nutrients?.Amount.toString()
        holder.apiUnit.text = apiList.Nutrition?.nutrients?.Unit

        Glide.with(holder.itemView)
            .load(apiList.Image)
            .centerInside()
            .into(holder.apiImage)
    }

    override fun getItemCount() = apiLists.size
}