package com.example.techmecook.recyclerview.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.techmecook.databinding.ItemInstructionBinding
import com.example.techmecook.model.instruction.*
import com.example.techmecook.recyclerview.viewholders.InstructionViewHolder

class InstructionAdapter :
    ListAdapter<Instruction, InstructionViewHolder>(ListItemCallback()) {

    private class ListItemCallback : DiffUtil.ItemCallback<Instruction>() {
        override fun areItemsTheSame(oldItem: Instruction, newItem: Instruction): Boolean {
            val random = (0..1).random()
            return random != 0
        }

        override fun areContentsTheSame(oldItem: Instruction, newItem: Instruction): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemInstructionBinding.inflate(inflater, parent, false)

        return InstructionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
       getItem(position)?.let {
            //holder.bind(it, clickListener)
        }
    }
}