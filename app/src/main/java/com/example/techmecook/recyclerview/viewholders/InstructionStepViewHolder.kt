package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.techmecook.databinding.ItemStepBinding
import com.example.techmecook.model.instruction.InstructionStep
import com.example.techmecook.recyclerview.click_listeners.InstructionStepClickListener

class InstructionStepViewHolder(private val binding: ItemStepBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
            instructionStep: InstructionStep,
            clickListener: InstructionStepClickListener
    ) {
        binding.step = instructionStep
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

}