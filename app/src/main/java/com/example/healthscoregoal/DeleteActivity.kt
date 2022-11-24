package com.example.healthscoregoal

import android.icu.text.Transliterator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : AppCompatActivity() {
    lateinit var dAB: Button
    lateinit var dSB: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        dSB = findViewById<Button>(R.id.delSleepButton)
        dAB = findViewById<Button>(R.id.delAllButton)


//       dSB.setOnClickListener{
//           lifecycleScope.launch(Dispatchers.IO) {
//               (application as FitnessApplication).db.fitnessDao().delete(fitness.toEntity())
//           }
//        }

        dAB.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                (application as FitnessApplication).db.fitnessDao().deleteAll()
            }
        }

    }
}