package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.techmecook.databinding.ItemInstructionBinding
import com.example.techmecook.databinding.ItemStepBinding
import com.example.techmecook.model.instruction.Instruction
import com.example.techmecook.model.instruction.InstructionStep
import com.example.techmecook.recyclerview.adapters.InstructionStepAdapter
import com.example.techmecook.recyclerview.click_listeners.InstructionClickListener
import com.example.techmecook.recyclerview.click_listeners.InstructionStepClickListener

class   InstructionViewHolder(private val binding: ItemInstructionBinding) : RecyclerView.ViewHolder(binding.root), InstructionStepClickListener {

    fun bind(
        instruction: Instruction,
        steps: List<InstructionStep>,
        clickListener: InstructionClickListener
    ) {
        binding.instruction = instruction
        binding.clickListener = clickListener
        binding.steps.adapter = InstructionStepAdapter(steps, this)
        binding.executePendingBindings()
    }

    override fun onClick(instruction: InstructionStep)
    {}

}