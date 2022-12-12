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
    @ColumnInfo(name = "foodNames") val foodsEn: String?,
    @ColumnInfo(name = "sugars") val sugarsEn: Int?,
    @ColumnInfo(name = "carbs") val carbsEn: Int?,
    @ColumnInfo(name = "calories") val caloriesEn: Int?,

)

// Exercise table
@Entity(tableName = "exercise_table")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "exercise day") val exDayEn: String?,
    @ColumnInfo(name = "exercise name") val exNameEn: String?,
    @ColumnInfo(name = "exercise length") val exTimeEn: String?,
    @ColumnInfo(name = "burntCals") val burntCalEn: Int?
)

