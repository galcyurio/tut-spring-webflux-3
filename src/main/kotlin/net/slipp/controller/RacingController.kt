package net.slipp.controller

import net.slipp.data.domain.racingcar.Car
import net.slipp.data.domain.racingcar.forwardAll
import net.slipp.data.dto.GameDto
import org.springframework.web.bind.annotation.*

/**
 * @author galcyurio
 */
@RequestMapping("/cars")
@RestController
class RacingController {

    @GetMapping("")
    fun cars(): List<Car> {
        // TODO: cars 반환하기
        return emptyList()
    }

    @PostMapping("/quick-start")
    fun start(@RequestBody gameDto: GameDto): List<Car> {
        val cars = gameDto.carNames.trim().split(",").asSequence()
            .map { Car(it) }
            .toList()

        forwardAll(cars, gameDto.laps)
        return cars
    }
}
