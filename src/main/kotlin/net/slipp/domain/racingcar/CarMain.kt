package net.slipp.domain.racingcar

import java.util.*

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).")
    val carNames = sc.nextLine().split(",")
    val carList = carNames.map { Car(it) }.toList()

    println(carList)
    println("시도할 회수는 몇회인가요?")
    val size = sc.nextInt()
    sc.close()

    println("----------------- result ------------------")
    forwardAll(carList, size)

    val maxList = findFastest(carList)
    println("${maxList.joinToString { it.name }} 최종 우승했습니다")
}