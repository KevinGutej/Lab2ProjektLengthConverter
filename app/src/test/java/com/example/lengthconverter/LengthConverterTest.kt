package com.example.lengthconverter

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LengthConverterTest {

    @Test
    fun testMetersConversion() {
        val meters = 1500.0

        val centimeters = meters * 100.0
        val kilometers = meters / 1000.0
        val miles = meters / 1609.344
        val lightYears = meters / 9460730472580800.0

        assertEquals(150000.0, centimeters, 0.001)
        assertEquals(1.5, kilometers, 0.0001)
        assertEquals(0.932056, miles, 0.0001)
        assertEquals(1.585e-13, lightYears, 1e-16)
    }

    @Test
    fun testInvalidInputHandling() {
        val invalidInput = "abc"
        val parsedValue = invalidInput.toDoubleOrNull()
        assertNull(parsedValue)
    }
}