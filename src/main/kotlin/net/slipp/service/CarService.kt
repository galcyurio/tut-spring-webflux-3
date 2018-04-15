package net.slipp.service

import net.slipp.data.domain.racingcar.Car
import net.slipp.data.domain.racingcar.Game
import net.slipp.data.domain.racingcar.forwardAll
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toFlux
import reactor.core.publisher.toMono
import java.time.Duration
import java.util.*

/**
 * @author galcyurio
 */
@Service
class CarService {

    companion object {
        private val games = HashMap<String, Game>()
    }

    fun createGame(laps: Int, cars: List<Car>): Mono<Game> {
        val id = UUID.randomUUID().toString()
        val game = Game(id, laps, cars)
        games[id] = game
        return game.toMono()
    }

    fun findGames(): Flux<Game> {
        return games.values.toFlux()
    }

    fun findGame(id: String): Mono<Game> {
        return games[id]!!.toMono()
    }

    fun forwardAll(gameId: String): Flux<Car> {
        val game = games[gameId]!!
        return game.cars
            .forwardAll(game.laps)
            .toFlux()
    }

    fun forwardAll(gameId: String, duration: Duration): Flux<HashMap<String, Any>> {
        val game = games[gameId]!!
        val interval = Flux.interval(duration).take(game.laps.toLong())
        val flux = (1..game.laps).toFlux()
            .map { lap ->
                HashMap<String, Any>().apply {
                    put("lap", lap)
                    put("cars", game.cars.forwardAll(1))
                }
            }
            .delayElements(duration)

        return Flux.zip(interval, flux).map { it.t2 }
    }
}
