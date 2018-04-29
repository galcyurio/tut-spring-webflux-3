package net.slipp.config

import net.slipp.handler.HelloHandler
import net.slipp.handler.TodoHandler
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
        "/hello".nest {
            GET("/", handler::hello)
        }
    }

    @Bean
    fun routeTodos(handler: TodoHandler): RouterFunction<ServerResponse> = router {
        "/todos".nest {
            GET("/", handler::findAll)
        }
    }

}