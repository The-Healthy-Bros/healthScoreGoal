package com.example.healthscoregoal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FitnessDAO {
    // Sleep table
    @Query("SELECT * FROM fitness_table")
    fun getAll(): Flow<List<FitnessEntity>>

    @Insert
    fun insert(fitness: FitnessEntity)

    @Delete
    fun delete(fitness: FitnessEntity)

    @Query("DELETE FROM fitness_table")
    fun deleteAll()

    // Calorie table
    @Query("SELECT * FROM cal_table")
    fun getAllCal(): Flow<List<CalEntity>>

    @Insert
    fun insert(calorie: CalEntity)

    @Delete
    fun deleteCal(calorie: CalEntity)

    @Query("DELETE FROM cal_table")
    fun deleteAllCal()

    // Exercise table
    @Query("SELECT * FROM exercise_table")
    fun getAllExercise(): Flow<List<ExerciseEntity>>

    @Insert
    fun insert(exercise: ExerciseEntity)

    @Delete
    fun deleteExercise(exercise: ExerciseEntity)

    @Query("DELETE FROM exercise_table")
    fun deleteAllExercise()
}