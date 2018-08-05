package com.example.gangadhar.tipcalculator.viewmodel

import android.app.Application
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.model.RestaurantCalculator
import com.example.gangadhar.tipcalculator.model.TipCalculation
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CalculatorViewModelTest {
    lateinit var calculatorViewModel: CalculatorViewModel

    @Mock
    lateinit var mockCalculator: RestaurantCalculator
    @Mock
    lateinit var application: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        stubResource(0.0, "$0.00")

        calculatorViewModel = CalculatorViewModel(application, mockCalculator)
    }

    private fun stubResource(given: Double, returnStub: String) {
        `when`(application.getString(R.string.dollar_amount, given)).thenReturn(returnStub)
    }

    @Test
    fun testCalculateTip() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"
        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.50)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")
        calculatorViewModel.calculateTip()
        assertEquals(10.00, calculatorViewModel.lastTipCalculation.checkAmount)
        assertEquals(1.50, calculatorViewModel.lastTipCalculation.tipAmount)
        assertEquals(11.50, calculatorViewModel.lastTipCalculation.grandTotal)

    }

    @Test
    fun testCalculateTipOutputBinding() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = "15"
        val stub = TipCalculation(checkAmount = 10.00, tipAmount = 1.5, grandTotal = 11.50)
        `when`(mockCalculator.calculateTip(10.00, 15)).thenReturn(stub)
        stubResource(10.0, "$10.00")
        stubResource(1.5, "$1.50")
        stubResource(11.5, "$11.50")

        calculatorViewModel.calculateTip()
        assertEquals("$10.00", calculatorViewModel.outputCheckAmount)
        assertEquals("$1.50", calculatorViewModel.outputTipAmount)
        assertEquals("$11.50", calculatorViewModel.outputGrandTotal)

    }

    @Test
    fun testCalculateTipBadTipPercent() {

        calculatorViewModel.inputCheckAmount = "10.00"
        calculatorViewModel.inputTipPercentage = ""

        calculatorViewModel.calculateTip()
        verify(mockCalculator, never()).calculateTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())

    }

    @Test
    fun testCalculateTipBadCheckInputAmount() {

        calculatorViewModel.inputCheckAmount = ""
        calculatorViewModel.inputTipPercentage = "15"

        calculatorViewModel.calculateTip()
        verify(mockCalculator, never()).calculateTip(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyInt())

    }

    @Test
    fun testSaveCurrentTip() {
        val stub = TipCalculation(checkAmount = 10.00, tipPercentage = 15, grandTotal = 11.5)
        fun setupCalculation() {
            calculatorViewModel.inputCheckAmount = "10.00"
            calculatorViewModel.inputTipPercentage = "15"
            `when`(mockCalculator.calculateTip(checkAmount = 10.00, tipPercentage = 15)).thenReturn(stub)
        }
        setupCalculation()
        calculatorViewModel.calculateTip()
        calculatorViewModel.saveCurrentTip("Green eggs and bacon")
        verify(mockCalculator).saveTipCalculation(stub.copy(locationName = "Green eggs and bacon"))
        assertEquals("Green eggs and bacon",calculatorViewModel.locationName)

    }


}