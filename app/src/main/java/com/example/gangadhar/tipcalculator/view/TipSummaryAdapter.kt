package com.example.gangadhar.tipcalculator.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.gangadhar.tipcalculator.R
import com.example.gangadhar.tipcalculator.databinding.SavedTipCalculationListItemBinding
import com.example.gangadhar.tipcalculator.viewmodel.TipCalculationSummaryItem

class TipSummaryAdapter(val onItemSelected:(item: TipCalculationSummaryItem) -> Unit) : RecyclerView.Adapter<TipSummaryAdapter.TipSummaryViewHolder>() {

    private val tipCalculationSummaries = mutableListOf<TipCalculationSummaryItem>()

    fun updateList(updates: List<TipCalculationSummaryItem>) {
        tipCalculationSummaries.clear()
        tipCalculationSummaries.addAll(updates)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return tipCalculationSummaries.size
    }

    override fun onBindViewHolder(holder: TipSummaryViewHolder, position: Int) {
        holder.bind(tipCalculationSummaries[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipSummaryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<SavedTipCalculationListItemBinding>(inflater, R.layout.saved_tip_calculation_list_item, parent, false)
        return TipSummaryViewHolder(binding)
    }

    inner class TipSummaryViewHolder(val binding: SavedTipCalculationListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TipCalculationSummaryItem) {
            binding.item = item
            binding.root.setOnClickListener{onItemSelected(item)}
            binding.executePendingBindings()
        }

    }

}