package com.example.gangadhar.tipcalculator.viewmodel

import android.app.Application
import android.databinding.BaseObservable
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.model.RestaurantCalculator
import com.example.gangadhar.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(app: Application, val calculator: RestaurantCalculator = RestaurantCalculator()) : ObservableViewModel(app) {
    var inputCheckAmount = ""
    var inputTipPercentage = ""
    var outputCheckAmount = ""
    var outputTipAmount = ""
    var outputGrandTotal = ""
    var tipCalculation = TipCalculation()

    init {
        updateOutputs(TipCalculation())
    }

    private fun updateOutputs(tipCalculation: TipCalculation) {

        outputCheckAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.checkAmount)
        outputTipAmount = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.tipAmount)
        outputGrandTotal = getApplication<Application>().getString(R.string.dollar_amount, tipCalculation.grandTotal)
    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercentage = inputTipPercentage.toIntOrNull()
        if (checkAmount != null && tipPercentage != null) {
            tipCalculation = calculator.calculateTip(checkAmount, tipPercentage)
            updateOutputs(tipCalculation)
            clearInputs()
        }

    }

    fun clearInputs() {
        inputCheckAmount = "0.00"
        inputTipPercentage = "0"
        notifyChange()
    }

}