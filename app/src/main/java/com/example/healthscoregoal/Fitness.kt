package com.example.healthscoregoal

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

data class Calories(
    val id: Long,
    val calories: Int?,
    val food: String?
): java.io.Serializable {

    fun toEntity(): CalEntity{
        return CalEntity(
            id = this.id,
            caloriesEn = this.calories,
            foodEn = this.food
        )
    }
}

data class Foods(
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