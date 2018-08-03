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
        val baseCase = TipCalculation(10.00)
        val testVals = listOf(baseCase.copy(tipPercentage = 25, tipAmount = 2.5, grandTotal = 12.50),
                baseCase.copy(tipPercentage = 15, tipAmount = 1.5, grandTotal = 11.50),
                baseCase.copy(tipPercentage = 18, tipAmount = 1.8, grandTotal = 11.80))
        testVals.forEach {
            assertEquals(it, calculator.calculateTip(it.checkAmount, it.tipPercentage)) }

    }
}