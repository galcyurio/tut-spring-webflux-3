package net.slipp.domain.racingcar

import java.util.*

data class Car(val name: String) {
    var count: Int = 0

    fun forward() {
        val num = Random().nextInt(10)
        if (num >= 4) {
            count++
        }
        println("$name : ${"-".repeat(count)}")
    }
}
