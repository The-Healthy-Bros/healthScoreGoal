package com.example.healthscoregoal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fitness_table")
data class FitnessEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "day") val dayText: String?,
    @ColumnInfo(name = "hoursSlept") val hoursSlept: Int?,
)