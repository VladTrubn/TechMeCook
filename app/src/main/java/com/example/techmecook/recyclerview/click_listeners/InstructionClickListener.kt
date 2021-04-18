package com.example.techmecook.recyclerview.click_listeners

import com.example.techmecook.model.instruction.Instruction

interface InstructionClickListener {
    fun onClick(ingredient: Instruction)
}