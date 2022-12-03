package com.example.healthscoregoal

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class Fitness(
    val id: Long,
    val dayText: String?,
    val hoursSlept: Int?
): java.io.Serializable {

    fun toEntity(): FitnessEntity{
        return FitnessEntity(
            id = this.id,
            dayText = this.dayText,
            hoursSlept = this.hoursSlept
        )
    }
}

data class Foods(
    val id: Long,
    val foodName: String,
    val sugars: Int?,
    val carbs: Int?,
    val calories: Int?,
): java.io.Serializable {

    fun toEntity(): CalEntity{
        return CalEntity(
            id = this.id,
            foodsEn = this.foodName,
            sugarsEn = this.sugars,
            carbsEn = this.carbs,
            caloriesEn = this.calories,
        )
    }
}

data class Exercises(
    val id: Long,
    val burntCals: Int?,
    val exercise: String?,
    val timeSpent: Double?
): java.io.Serializable {

    fun toEntity(): ExerciseEntity{
        return ExerciseEntity(
            id = this.id,
            burntCalEn = this.burntCals,
            exerciseEn = this.exercise,
            timeEn = this.timeSpent
        )
    }
}

@Keep
@Serializable
data class ApiTest(
    @SerialName("title")
    val Title: String?,
    @SerialName("image")
    val Image: String?,
    @SerialName("nutrition")
    val Nutrition: nutrition?
) : java.io.Serializable
@Keep
@Serializable
data class nutrition(
    @SerialName("nutrients")
    val nutrients: nutrients?
) : java.io.Serializable
@Keep
@Serializable
data class nutrients(
    @SerialName("name")
    val Name: String?,
    @SerialName("unit")
    val Unit: String?,
    @SerialName("amount")
    val Amount: Int?
) : java.io.Serializable