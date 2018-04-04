package net.slipp.annotations

import org.springframework.stereotype.Component

/**
 * @author galcyurio
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class Handler