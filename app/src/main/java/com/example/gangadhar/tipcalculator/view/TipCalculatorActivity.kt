package com.example.gangadhar.tipcalculator.view

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.databinding.ActivityTipcalculatorBinding
import com.example.gangadhar.tipcalculator.viewmodel.CalculatorViewModel

class TipCalculatorActivity : AppCompatActivity(), SaveDialogFragment.Callback {
    override fun onSaveTip(locationName: String) {
        Snackbar.make(binding.root,"Saved $locationName",Snackbar.LENGTH_SHORT ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action_save -> {
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveFragment = SaveDialogFragment()
        saveFragment.show(supportFragmentManager,"SaveDialog")
    }

    lateinit var binding: ActivityTipcalculatorBinding
    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tipcalculator)
        binding.calculator = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
        setSupportActionBar(binding.toolbar)
    }

}
