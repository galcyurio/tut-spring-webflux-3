package net.slipp.handler

import net.slipp.annotations.Handler
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

/**
 * @author galcyurio
 */
@Handler
class HelloHandler {
    fun hello(request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(Mono.just("Hello, handler mono!"))
    }
}