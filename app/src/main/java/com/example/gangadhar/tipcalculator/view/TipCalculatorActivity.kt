package com.example.gangadhar.tipcalculator.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.databinding.ActivityTipcalculatorBinding
import com.example.gangadhar.tipcalculator.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity() {
    lateinit var binding: ActivityTipcalculatorBinding
    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tipcalculator)
        binding.calculator = CalculatorViewModel()
        setSupportActionBar(binding.toolbar)

    }


}
