package com.example.healthscoregoal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FitnessEntity::class, CalEntity::class, ExerciseEntity::class], version = 1)
abstract class FitnessDataBase : RoomDatabase() {

    abstract fun fitnessDao(): FitnessDAO

    companion object {

        @Volatile
        private var INSTANCE: FitnessDataBase? = null

        fun getInstance(context: Context): FitnessDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FitnessDataBase::class.java, "Fitness-db"
            ).build()
    }
}