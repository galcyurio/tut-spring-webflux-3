package net.slipp.controller

import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono


/**
 * @author galcyurio
 */
// @RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, world!"
    }

    @GetMapping("/mono")
    fun mono(): Mono<String> {
        return Mono.just("Hello, mono!")
    }
}
