package net.slipp.data.domain.racingcar

import java.util.*

data class Car(val name: String) {
    var count: Int = 0

    fun forward(): Boolean {
        val num = Random().nextInt(10)
        var isForward = false

        if (num >= 4) {
            count++
            isForward = true
        }
        return isForward
    }
}
