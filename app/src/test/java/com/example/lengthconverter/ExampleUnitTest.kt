package com.example.lengthconverter

import org.junit.Test
import org.junit.Assert.*

class ExampleUnitTest {

    @Test
    fun metersToCentimeters_isCorrect() {
        val meters = 5.0
        val centimeters = meters * 100
        assertEquals(500.0, centimeters, 0.001)
    }

    @Test
    fun metersToKilometers_isCorrect() {
        val meters = 2500.0
        val kilometers = meters / 1000
        assertEquals(2.5, kilometers, 0.001)
    }

    @Test
    fun invalidInput_returnsNull() {
        val input = "abc"
        val result = input.toDoubleOrNull()
        assertNull(result)
    }
}