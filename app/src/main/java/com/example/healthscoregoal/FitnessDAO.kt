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
    fun insertCal(calorie: CalEntity)

    @Delete
    fun deleteCal(calorie: CalEntity)

    @Query("DELETE FROM cal_table")
    fun deleteAllCal()

    // Exercise table
    @Query("SELECT * FROM exercise_table")
    fun getAllExercise(): Flow<List<ExerciseEntity>>

    @Insert
    fun insertEX(exercise: ExerciseEntity)

    @Delete
    fun deleteExercise(exercise: ExerciseEntity)

    @Query("DELETE FROM exercise_table")
    fun deleteAllExercise()

    // sugar api table
    @Query("SELECT * FROM api_sugar")
    fun getAllminmaxS(): Flow<List<APISUgar>>

    @Insert
    fun insertS(fitness: APISUgar)

    @Delete
    fun deleteSugar(fitness: APISUgar)

    @Query("DELETE FROM api_sugar")
    fun deleteAllS()

    // carb api table
    @Query("SELECT * FROM api_carbs")
    fun getAllminmaxCarbs(): Flow<List<APICarbs>>

    @Insert
    fun insertCarbs(fitness: APICarbs)

    @Delete
    fun deleteCarbs(fitness: APICarbs)

    @Query("DELETE FROM api_carbs")
    fun deleteAllCarbs()

    // cals api table
    @Query("SELECT * FROM api_cals")
    fun getAllminmaxCals(): Flow<List<APICals>>

    @Insert
    fun insertCals(fitness: APICals)

    @Delete
    fun deleteCals(fitness: APICals)

    @Query("DELETE FROM api_cals")
    fun deleteAllCals()
}