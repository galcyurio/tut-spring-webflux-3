package com.github.galcyurio.racingcar

import net.slipp.domain.racingcar.Car
import net.slipp.domain.racingcar.findFastest
import net.slipp.domain.racingcar.forwardAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks

class CarFunctionsTest {

    @Mock lateinit var car1: Car
    @Mock lateinit var car2: Car

    @Before
    fun setUp() {
        initMocks(this)
    }

    @Test
    fun forwardAllTest() {
        val num = 5
        forwardAll(listOf(car1, car2), num)

        verify(car1, times(num)).forward()
        verify(car2, times(num)).forward()
    }

    @Test
    fun findFastestTest__두_개의_car_의_count_가_다를때_가장_높은_count_를_가진_car_가_반환되어야_한다() {
        `when`(car1.count).thenReturn(3)
        `when`(car2.count).thenReturn(5)

        val actual = findFastest(listOf(car1, car2))

        assertThat(actual.size).isEqualTo(1)
        assertThat(actual[0]).isEqualTo(car2)
    }

    @Test
    fun findFastestTest__두_개의_car_의_count_가_같을때_두_개의_car_list_가_반환되어야_한다() {
        `when`(car1.count).thenReturn(3)
        `when`(car2.count).thenReturn(3)

        val actual = findFastest(listOf(car1, car2))

        assertThat(actual.size).isEqualTo(2)
        assertThat(actual).contains(car1, car2)
    }
}