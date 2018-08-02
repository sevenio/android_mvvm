package com.example.gangadhar.tipcalculator.model

class RestaurantCalculator{
    fun calculateTip(checkAmount:Double,tipPercentage :Int):TipCalculation{
        val tipAmount = checkAmount * (tipPercentage.toDouble()/100.0)
        val grandTotal = checkAmount + tipAmount
        return TipCalculation(checkAmount,
                tipPercentage,
                tipAmount,
                grandTotal)
    }

}