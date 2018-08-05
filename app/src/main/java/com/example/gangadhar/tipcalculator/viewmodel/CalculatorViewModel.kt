package com.example.gangadhar.tipcalculator.viewmodel

import android.app.Application
import android.databinding.Bindable
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.model.RestaurantCalculator
import com.example.gangadhar.tipcalculator.model.TipCalculation

class CalculatorViewModel @JvmOverloads constructor(app: Application, val calculator: RestaurantCalculator = RestaurantCalculator()) : ObservableViewModel(app) {
    var lastTipCalculation = TipCalculation()

    @Bindable
    var inputCheckAmount = ""
    @Bindable
    var inputTipPercentage = ""
    @get:Bindable
    val outputCheckAmount
        get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculation.checkAmount)
    @get:Bindable
    val outputTipAmount
        get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculation.tipAmount)
    @get:Bindable
    val outputGrandTotal
        get() = getApplication<Application>().getString(R.string.dollar_amount, lastTipCalculation.grandTotal)

    @get:Bindable
    val locationName get() = lastTipCalculation.locationName
    init {
        updateOutputs(TipCalculation())
    }


    fun saveCurrentTip(name: String){
        val tipToSave = lastTipCalculation.copy(locationName = name)
        calculator.saveTipCalculation(tipToSave)
        updateOutputs(tipToSave)
    }
    private fun updateOutputs(tipCalculation: TipCalculation) {

        lastTipCalculation = tipCalculation
        notifyChange()

    }

    fun calculateTip() {
        val checkAmount = inputCheckAmount.toDoubleOrNull()
        val tipPercentage = inputTipPercentage.toIntOrNull()
        if (checkAmount != null && tipPercentage != null) {
            lastTipCalculation = calculator.calculateTip(checkAmount, tipPercentage)
            updateOutputs(lastTipCalculation)

        }

    }


}