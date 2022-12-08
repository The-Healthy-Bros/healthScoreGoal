package com.example.healthscoregoal

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ExerciseFrag : Fragment() {
    private val exercise = mutableListOf<Exercises>()
    lateinit var eRV: RecyclerView
    lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_exercise, container, false)
        val layoutManager = LinearLayoutManager(view.context)
        eRV = view.findViewById(R.id.exRV)
        eRV.layoutManager = layoutManager
        exerciseAdapter = ExerciseAdapter(view.context, exercise)
        eRV.adapter = exerciseAdapter
        lifecycleScope.launch {
            (activity?.application as FitnessApplication).db.fitnessDao().getAllExercise()
                .collect { databaseList ->
                    databaseList.map { entity ->
                        Exercises(
                            entity.id,
                            entity.exDayEn,
                            entity.exNameEn,
                            entity.exTimeEn,
                            entity.burntCalEn
                        )
                    }.also { mappedList ->
                        Log.i("found", mappedList.size.toString())
                        exercise.clear()
                        exercise.addAll(mappedList)
                        exerciseAdapter.notifyDataSetChanged()
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
                    (activity?.application as FitnessApplication).db.fitnessDao().deleteExercise(exercise[position].toEntity())
                }
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(eRV)
        return view
    }
}