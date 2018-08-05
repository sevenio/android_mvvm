package com.example.gangadhar.tipcalculator.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import com.example.gangadhar.tipcalculator.R

class SaveDialogFragment : DialogFragment() {

    interface Callback {
        fun onSaveTip(locationName: String)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        saveTipCallback = context as? Callback
    }

    override fun onDetach() {
        super.onDetach()
        saveTipCallback = null
    }

    private var saveTipCallback: SaveDialogFragment.Callback? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val saveDialog = context?.let { context ->
            val editText = EditText(context)
            editText.id = viewId
            editText.hint = getString(R.string.enter_location)
            AlertDialog.Builder(context)
                    .setView(editText)
                    .setPositiveButton(getString(R.string.save), { _, _ -> onSave(editText) })
                    .setNegativeButton(getString(R.string.cancel), null)
                    .create()
        }
        return saveDialog!!
    }

    private fun onSave(editText: EditText) {
        val text = editText.text.toString()
        if (text.isNotEmpty()) {
            saveTipCallback?.onSaveTip(text)
        }
    }

    companion object {
        val viewId = View.generateViewId()
    }
}