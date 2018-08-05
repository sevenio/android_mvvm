package com.example.gangadhar.tipcalculator.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import java.math.RoundingMode

class RestaurantCalculator(val tipCalculationRepository: TipCalculationRepository = TipCalculationRepository()) {
    fun calculateTip(checkAmount: Double, tipPercentage: Int): TipCalculation {
        val tipAmount = (checkAmount * (tipPercentage.toDouble() / 100.0))
                .toBigDecimal()
                .setScale(2, RoundingMode.HALF_UP)
                .toDouble()
        val grandTotal = checkAmount + tipAmount
        return TipCalculation(checkAmount = checkAmount,
                tipPercentage = tipPercentage,
                tipAmount = tipAmount,
                grandTotal = grandTotal)
    }

    fun saveTipCalculation(tipCalculation: TipCalculation) {
        tipCalculationRepository.saveTipCalculation(tipCalculation)
    }

    fun loadTipCalculationByName(locationName: String): TipCalculation? {
        return tipCalculationRepository.loadTipCalculationByName(locationName)
    }

    fun loadSavedTipCalculations(): LiveData<List<TipCalculation>> {
        return tipCalculationRepository.loadSavedTipCalculations()
    }

}