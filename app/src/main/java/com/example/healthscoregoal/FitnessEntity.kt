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
// Exercise table
@Entity(tableName = "api_sugar")
data class APISUgar(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "min sugar") val exMinS: String?,
    @ColumnInfo(name = "max sugar") val exMaxS: String?
)
@Entity(tableName = "api_carbs")
data class APICarbs(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "min carb") val exMinCarb: String?,
    @ColumnInfo(name = "max carb") val exMaxCarb: String?
)
@Entity(tableName = "api_cals")
data class APICals(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "min cals") val exMinCals: String?,
    @ColumnInfo(name = "max cals") val exMaxCals: String?
)
