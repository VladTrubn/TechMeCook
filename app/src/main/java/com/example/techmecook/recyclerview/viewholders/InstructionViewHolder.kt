package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.techmecook.databinding.ItemInstructionBinding
import com.example.techmecook.model.instruction.Instruction
import com.example.techmecook.recyclerview.click_listeners.InstructionClickListener

class InstructionViewHolder(private val binding: ItemInstructionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        instruction: Instruction,
        clickListener: InstructionClickListener
    ) {
        binding.instruction = instruction
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

}