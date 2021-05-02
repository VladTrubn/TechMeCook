package com.example.techmecook.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.techmecook.databinding.ItemStepBinding
import com.example.techmecook.model.instruction.*
import com.example.techmecook.recyclerview.click_listeners.InstructionStepClickListener
import com.example.techmecook.recyclerview.viewholders.InstructionStepViewHolder

class InstructionStepAdapter(
    private val steps: List<InstructionStep>,
    private val clickListener: InstructionStepClickListener
): ListAdapter<InstructionStep, InstructionStepViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<InstructionStep>() {
        override fun areItemsTheSame(oldItem: InstructionStep, newItem: InstructionStep): Boolean {
            return oldItem.step==newItem.step
        }

        override fun areContentsTheSame(oldItem: InstructionStep, newItem: InstructionStep): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionStepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStepBinding.inflate(inflater, parent, false)

        return InstructionStepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstructionStepViewHolder, position: Int) {
        this.steps[position].let {
            holder.bind(it, clickListener)
        }
    }

    override fun getItemCount(): Int {
        return steps.size
    }
}