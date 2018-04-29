package net.slipp.handler

import net.slipp.annotations.Handler
import net.slipp.repository.TodoItemRepository
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Handler
class TodoHandler(private val todoItemRepository: TodoItemRepository) {

    fun findAll(request: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().contentType(APPLICATION_JSON_UTF8)
            .body(todoItemRepository.findAll())

}