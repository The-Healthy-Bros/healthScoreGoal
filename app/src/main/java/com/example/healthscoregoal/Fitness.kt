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