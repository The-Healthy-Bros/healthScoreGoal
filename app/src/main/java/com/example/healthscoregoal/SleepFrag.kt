package com.example.healthscoregoal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "SleepFrag/"

class SleepFrag : Fragment() {
    private val sleep = mutableListOf<Fitness>()
    lateinit var sRV: RecyclerView
    lateinit var sleepAdapter: SleepAdapter
    lateinit var addB: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_sleep, container, false)
        val layoutManager = LinearLayoutManager(view.context)
        sRV = view.findViewById(R.id.sleepRV)
        sRV.layoutManager = layoutManager
        sleepAdapter = SleepAdapter(view.context, sleep)
        sRV.adapter = sleepAdapter
        lifecycleScope.launch {
            (activity?.application as FitnessApplication).db.fitnessDao().getAll()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        Fitness(
                            entity.id,
                            entity.dayText,
                            entity.hoursSlept,
                        )
                    }.also { mappedList ->
                        Log.i("found", mappedList.size.toString())
                        sleep.clear()
                        sleep.addAll(mappedList)
                        sleepAdapter.notifyDataSetChanged()
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
                    (activity?.application as FitnessApplication).db.fitnessDao().delete(sleep[position].toEntity())
                }
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(sRV)
        addB = view.findViewById(R.id.sleepButton1)
        addB.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
           this.startActivity(intent)
       }
        return view
    }
}