package com.example.healthscoregoal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Sleep table
@Entity(tableName = "fitness_table")
data class FitnessEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "day") val dayText: String?,
    @ColumnInfo(name = "hoursSlept") val hoursSlept: Int?
)

// Calorie table - En is for the entities name
@Entity(tableName = "cal_table")
data class CalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "calories") val caloriesEn: Int?,
    @ColumnInfo(name = "food") val foodEn: String?
)

// Exercise table
@Entity(tableName = "exercise_table")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "burntCals") val burntCalEn: Int?,
    @ColumnInfo(name = "exercise") val exerciseEn: String?,
    @ColumnInfo(name = "time") val timeEn: Double?
)