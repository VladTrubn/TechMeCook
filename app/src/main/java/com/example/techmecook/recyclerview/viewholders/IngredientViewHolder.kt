package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.techmecook.databinding.ItemIngredientInrecipeBinding
import com.example.techmecook.model.ingredient.IngredientGeneralInfo
import com.example.techmecook.recyclerview.click_listeners.IngredientClickListener

class IngredientViewHolder(private val binding: ItemIngredientInrecipeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        ingredient: IngredientGeneralInfo,
        clickListener: IngredientClickListener
    ) {
        binding.ingredient = ingredient
        binding.clickListener = clickListener
        binding.executePendingBindings()
    }

}