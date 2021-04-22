package com.example.techmecook.recyclerview.click_listeners
import com.example.techmecook.model.instruction.InstructionStep

interface InstructionStepClickListener {
    fun onClick(instruction: InstructionStep)
}