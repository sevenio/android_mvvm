package com.example.gangadhar.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.view.LayoutInflater
import android.view.View
import com.example.gangadhar.tipcalculator.R
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
        return recyclerView
    }
}