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
    val exDay: String?,
    val exName: String?,
    val exTime: String?,
    val burntCals: Int?
): java.io.Serializable {

    fun toEntity(): ExerciseEntity{
        return ExerciseEntity(
            id = this.id,
            exDayEn = this.exDay,
            exNameEn = this.exName,
            exTimeEn = this.exTime,
            burntCalEn = this.burntCals,
        )
    }
}
data class APISug(
    val id: Long,
    val minSug: String?,
    val maxSug: String?,
): java.io.Serializable {

    fun toEntity(): APISUgar{
        return APISUgar(
            id = this.id,
            exMinS = this.minSug,
            exMaxS = this.maxSug
        )
    }
}
data class APICarb(
    val id: Long,
    val minCarb: String?,
    val maxCarb: String?,
): java.io.Serializable {

    fun toEntity(): APICarbs{
        return APICarbs(
            id = this.id,
            exMinCarb = this.minCarb,
            exMaxCarb = this.maxCarb
        )
    }
}
data class APICal(
    val id: Long,
    val minCal: String?,
    val maxCal: String?,
): java.io.Serializable {

    fun toEntity(): APICals{
        return APICals(
            id = this.id,
            exMinCals = this.minCal,
            exMaxCals = this.maxCal
        )
    }
}
@Keep
@Serializable
data class BaseResponse(
    @SerialName("results")
    val results: List<ApiTest>?
)

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
    val nutrients: List<nutrients>?
) : java.io.Serializable
@Keep
@Serializable
data class nutrients(
    @SerialName("name")
    val Name: String?,
    @SerialName("unit")
    val Unit: String?,
    @SerialName("amount")
    val Amount: Float?
) : java.io.Serializable