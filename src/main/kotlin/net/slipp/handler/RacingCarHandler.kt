package net.slipp.handler

import net.slipp.annotations.Handler
import net.slipp.domain.racingcar.Car
import net.slipp.domain.racingcar.forwardAll
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Handler
class RacingCarHandler {

    fun racingStart(request: ServerRequest) = request.bodyToMono(Game::class.java).flatMap { game ->
        val cars = game.rawCars.trim().split(",").asSequence()
            .map { Car(it) }
            .toMutableList()
        forwardAll(cars, game.laps)
        ServerResponse.ok().body(Mono.just(cars))
    }
}

data class Game(
    val rawCars: String,
    val laps: Int
)