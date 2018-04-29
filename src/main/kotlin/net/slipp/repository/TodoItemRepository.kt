package net.slipp.repository

import net.slipp.data.domain.TodoItem
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface TodoItemRepository: ReactiveCrudRepository<TodoItem, String>