package net.slipp.data.domain.racingcar

/**
 * @author galcyurio
 */
data class Game(
    val id: String,
    val laps: Int,
    val cars: List<Car>
)