package com.example.healthscoregoal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "foodFrag/"

class FoodFrag : Fragment() {
    private val food = mutableListOf<Foods>()
    lateinit var fRV: RecyclerView
    lateinit var foodAdapter: FoodAdapter
    lateinit var sugarButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food, container, false)
        val layoutManager = LinearLayoutManager(view.context)
        fRV = view.findViewById(R.id.foodRV)
        sugarButton = view.findViewById(R.id.sugarButton)
        fRV.layoutManager = layoutManager
        foodAdapter = FoodAdapter(view.context, food)
        fRV.adapter = foodAdapter
        sugarButton.setOnClickListener{
            val intent = Intent(it.context, SugarActivity::class.java)
            this.startActivity(intent)
        }
        lifecycleScope.launch {
            (activity?.application as FitnessApplication).db.fitnessDao().getAllCal()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        Foods(
                            entity.id,
                            entity.foodsEn.toString(),
                            entity.sugarsEn,
                            entity.carbsEn,
                            entity.caloriesEn
                        )
                    }.also { mappedList ->
                        Log.i("found", mappedList.size.toString())
                        food.clear()
                        food.addAll(mappedList)
                        foodAdapter.notifyDataSetChanged()
                    }
                }
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.absoluteAdapterPosition
                lifecycleScope.launch(Dispatchers.IO) {
                    (activity?.application as FitnessApplication).db.fitnessDao().deleteCal(food[position].toEntity())
                }
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(fRV)
        return view
    }
}