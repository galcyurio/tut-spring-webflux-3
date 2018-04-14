package net.slipp.data.domain.racingcar

fun fromRawCars(cars: String): List<Car> {
    return cars.trim().split(",").asSequence()
        .map { Car(it) }
        .toList()
}

fun <T : List<Car>> T.forwardAll(laps: Int): List<Car> {
    (1..laps).forEach {
        this.forEach { it.forward() }
    }
    return this
}

fun findFastest(carList: List<Car>): List<Car> {
    val maxCount = carList.maxBy { it.count }?.count
    return carList.filter { it.count == maxCount }
}