package com.example.gangadhar.tipcalculator.model

import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class RestaurantCalculatorTest {
    lateinit var calculator: RestaurantCalculator
    @Before
    fun setup() {
        calculator = RestaurantCalculator()
    }

    @Test
    fun testCalculateTip() {
        val checkInput = 10.00
        val tipPercentage = 25
        val expectedTipResult = TipCalculation(checkInput,
                tipPercentage,
                tipAmount = 2.50,
                grandTotal = 12.50)
        assertEquals(expectedTipResult, calculator.calculateTip(checkInput,tipPercentage))
    }
}