package net.slipp.data.domain

import java.time.LocalDate

/**
 * @author galcyurio
 */
data class TodoItem(
    val id: String? = null,
    val done: Boolean = false,
    val subject: String = "제목",
    val deadline: LocalDate? = null
)