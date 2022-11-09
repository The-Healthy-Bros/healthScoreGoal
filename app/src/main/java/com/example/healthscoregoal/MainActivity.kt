package com.example.healthscoregoal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    // For controlling the bottom nav view
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Defining the Fragments here
        val mainFrag: Fragment = MainFrag()
        val foodFrag: Fragment = FoodFrag()
        val exerciseFrag: Fragment = ExerciseFrag()
        val settingsFrag: Fragment = SettingsFrag()

        // Linking the bottom navigation view
        bottomNavView = findViewById(R.id.bottom_navigation)

        // Handle Navigation Selection
        bottomNavView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.main_menu -> fragment = mainFrag
                R.id.food_menu -> fragment = foodFrag
                R.id.exercise_menu -> fragment = exerciseFrag
                R.id.setting_menu -> fragment = settingsFrag
            }
            replaceFragment(fragment)
            true
        }
        bottomNavView.selectedItemId = R.id.main_menu

    }

    // This function is used to start the next fragment selected
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.all_frame_layout, fragment)
        fragmentTransaction.commit()
    }
}