package net.slipp.controller

import net.slipp.data.domain.racingcar.Car
import net.slipp.data.domain.racingcar.Game
import net.slipp.data.domain.racingcar.fromRawCars
import net.slipp.service.CarService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

/**
 * @author galcyurio
 */
@RequestMapping("/cars")
@RestController
class CarController(
    val carService: CarService
) {

    @GetMapping("")
    fun findGames(): Flux<Game> {
        return carService.findGames()
    }

    @GetMapping("/{id}")
    fun findGame(@PathVariable("id") id: String): Mono<Game> {
        return carService.findGame(id)
    }

    @PostMapping("")
    fun createGame(@RequestBody gameMap: Map<String, Any>): Mono<Game> {
        return carService.createGame(gameMap["laps"] as Int, fromRawCars(gameMap["carNames"] as String))
    }

    @PostMapping("/{id}/quick-start")
    fun quickStart(@PathVariable("id") id: String): Flux<Car> {
        return carService.forwardAll(id)
    }

    @GetMapping("/{id}/stream-start", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun streamStart(@PathVariable("id") id: String): Flux<HashMap<String, Any>> {
        return carService.forwardAll(id, Duration.ofMillis(300))
    }
}
