package com.example.healthscoregoal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDAO {
    @Query("SELECT * FROM fitness_table")
    fun getAll(): Flow<List<FitnessEntity>>

    @Insert
    fun insert(fitness: FitnessEntity)

    @Delete
    fun delete(fitness: FitnessEntity)

    @Query("DELETE FROM fitness_table")
    fun deleteAll()
}