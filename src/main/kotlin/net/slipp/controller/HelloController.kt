package net.slipp.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration


/**
 * @author galcyurio
 */
@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello(): String {
        return "Hello, world!"
    }

    @GetMapping("/mono")
    fun mono(): Mono<String> {
        return Mono.just("Hello, mono!")
    }

    @GetMapping("/event-stream", produces = [(MediaType.TEXT_EVENT_STREAM_VALUE)])
    fun eventStream(): Flux<HashMap<String, Any>> {
        return Flux.fromIterable(1..100)
            .delayElements(Duration.ofMillis(500))
            .map {
                HashMap<String, Any>().apply {
                    put("before", it)
                    put("after", "Hello, event-stream $it")
                }
            }
    }
}
