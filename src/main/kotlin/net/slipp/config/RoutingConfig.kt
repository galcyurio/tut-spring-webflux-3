package net.slipp.config

import net.slipp.handler.HelloHandler
import net.slipp.handler.RacingCarHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router

/**
 * @author galcyurio
 */
@Configuration
class RoutingConfig {

    @Bean
    fun routeHello(handler: HelloHandler) : RouterFunction<ServerResponse> = router {
        "/".nest {
            GET("/hello", handler::hello)
        }
    }

    @Bean
    fun routeRacingCar(handler: RacingCarHandler) : RouterFunction<ServerResponse> = router {
        "/racing".nest {
            GET("/") { ServerResponse.ok().render("role") }
            POST("/start", handler::racingStart)
        }
    }
}