package com.example.gangadhar.tipcalculator.model

import android.arch.core.executor.testing.InstantTaskExecutorRule
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TipCalculationRepositoryTest {

    lateinit var tipCalculationRepository: TipCalculationRepository

    //this is a junit test rule which is run before and after the any test
    @get: Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        tipCalculationRepository = TipCalculationRepository()
    }

    @Test
    fun testSaveTip() {
        val tip = TipCalculation("Pancake Paradise", 100.0, 25, 25.0, 125.0)
        tipCalculationRepository.saveTipCalculation(tip)
        assertEquals(tip, tipCalculationRepository.loadTipCalculationByName(tip.locationName))
    }

    @Test
    fun testLoadSavedTipcalculations() {
        val tip1 = TipCalculation("Pancake Paradise", 100.0, 25, 25.0, 125.0)
        val tip2 = TipCalculation("Veggie Sensation", 100.0, 25, 25.0, 125.0)
        tipCalculationRepository.saveTipCalculation(tip1)
        tipCalculationRepository.saveTipCalculation(tip2)

        tipCalculationRepository.loadSavedTipCalculations().observeForever { tipCalculations: List<TipCalculation>? -> assertEquals(2, tipCalculations?.size) }


    }
}