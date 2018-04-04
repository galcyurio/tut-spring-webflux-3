package com.github.galcyurio

import net.slipp.domain.Person
import org.junit.Assert.assertEquals
import org.junit.Test

class PersonTest {

    @Test
    fun maxAge() {
        val personList = listOf(
            Person("aaa", 15),
            Person("bbb"),
            Person("ccc", 30)
        )

        val max = personList.maxBy { it.age ?: 0 }
        assertEquals(max?.name, "ccc")
    }
}