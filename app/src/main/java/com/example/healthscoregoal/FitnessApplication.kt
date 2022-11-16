package com.example.healthscoregoal

import android.app.Application

class FitnessApplication : Application() {
    val db by lazy { FitnessDataBase.getInstance(this) }
}