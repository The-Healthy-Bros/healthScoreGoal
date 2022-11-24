package com.example.healthscoregoal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity/"
class MainActivity : AppCompatActivity() {
    // For controlling the bottom nav view
    private val fitness = mutableListOf<Fitness>()
    lateinit var newB: Button
    //lateinit var delB: Button
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Defining the Fragments here
        val mainFrag: Fragment = MainFrag()
        val foodFrag: Fragment = FoodFrag()
        val exerciseFrag: Fragment = ExerciseFrag()
        val sleepFrag: Fragment = SleepFrag()
        val settingsFrag: Fragment = SettingsFrag()

        // Linking the bottom navigation view
        bottomNavView = findViewById(R.id.bottom_navigation)

        newB = findViewById<Button>(R.id.fitButton)
        //delB = findViewById<Button>(R.id.delButton)

        // Handle Navigation Selection
        bottomNavView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.main_menu -> fragment = mainFrag
                R.id.food_menu -> fragment = foodFrag
                R.id.exercise_menu -> fragment = exerciseFrag
                R.id.sleep_menu -> fragment = sleepFrag
                R.id.setting_menu -> fragment = settingsFrag
            }
            replaceFragment(fragment)


            val sleepInfo = intent.getSerializableExtra("EXTRA_ENTRY") as Fitness?

            if(sleepInfo != null) {
                Log.d(TAG, "got extra")
                lifecycleScope.launch(Dispatchers.IO) {
                    (application as FitnessApplication).db.fitnessDao().insert(
                        FitnessEntity(
                            id = sleepInfo.id,
                            dayText = sleepInfo.dayText,
                            hoursSlept = sleepInfo.hoursSlept
                        )
                    )
                }
                intent.removeExtra("EXTRA_ENTRY")
            }

            true
        }
        bottomNavView.selectedItemId = R.id.main_menu


        newB.setOnClickListener {
            val intent = Intent (this, DetailActivity::class.java)
            this.startActivity(intent)
        }
//        delB.setOnClickListener {
//            val intent = Intent (this, DeleteActivity::class.java)
//            this.startActivity(intent)
//        }

    }

    // This function is used to start the next fragment selected
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.all_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}