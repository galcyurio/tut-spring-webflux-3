package net.slipp.data.domain.racingcar

fun forwardAll(carList: List<Car>, laps: Int) {
    (1..laps).forEach {
        carList.forEach(Car::forward)
    }
}

fun findFastest(carList: List<Car>) : List<Car> {
    val maxCount = carList.maxBy { it.count }?.count
    return carList.filter { it.count == maxCount }
}