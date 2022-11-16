package com.example.healthscoregoal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

private const val TAG = "SleepFrag/"

class SleepFrag : Fragment() {
    private val sleep = mutableListOf<Fitness>()
    lateinit var sRV: RecyclerView
    lateinit var sleepAdapter: SleepAdapter

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
        return view
    }
}