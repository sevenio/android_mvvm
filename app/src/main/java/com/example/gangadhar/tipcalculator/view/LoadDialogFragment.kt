package com.example.gangadhar.tipcalculator.view

import android.app.Dialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.viewmodel.CalculatorViewModel
import kotlinx.android.synthetic.main.saved_tip_calculations_list.view.*

class LoadDialogFragment : DialogFragment() {

    interface Callback {
        fun onTipSelected(name: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        loadTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        loadTipCallback = null
    }

    private var loadTipCallback: Callback? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = context?.let { context ->
            AlertDialog.Builder(context)
                    .setView(createView(context))
                    .setNegativeButton(R.string.cancel, null)
                    .create()
        }
        return dialog!!
    }

    private fun createView(context: Context?): View {
        val recyclerView = LayoutInflater
                .from(context)
                .inflate(R.layout.saved_tip_calculations_list, null)
                .recycler_saved_calculations
        recyclerView.setHasFixedSize(true)
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val adapter = TipSummaryAdapter {
            loadTipCallback?.onTipSelected(it.locationName)
            dismiss()
        }
        recyclerView.adapter = adapter
        val viewModel = activity?.let { ViewModelProviders.of(it).get(CalculatorViewModel::class.java) }
        viewModel?.loadSavedTipCalculationSummaries()?.observe(this, Observer {
            if (it != null) {
                adapter.updateList(it)
            }
        })
        return recyclerView
    }
}