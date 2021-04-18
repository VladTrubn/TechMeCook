package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.techmecook.databinding.ItemInstructionBinding
import com.example.techmecook.model.instruction.Instruction

class InstructionViewHolder(private val binding: ItemInstructionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        instruction: Instruction
    ) {
        binding.instruction = instruction
        binding.executePendingBindings()
    }

}