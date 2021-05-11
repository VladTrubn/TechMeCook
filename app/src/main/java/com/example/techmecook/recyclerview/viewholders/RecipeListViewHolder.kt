package com.example.techmecook.recyclerview.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techmecook.databinding.ItemRecipeBinding
import com.example.techmecook.model.recipe.RecipeLight
import com.example.techmecook.recyclerview.click_listeners.RecipeClickListener

class RecipeListViewHolder(private val binding: ItemRecipeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        recipe: RecipeLight,
        clickListener: RecipeClickListener
    ) {
        binding.recipeTitle.text = recipe.title
        binding.layout.setOnClickListener { clickListener.onClick(recipe) }
        Glide.with(binding.root.context)
            .load(recipe.image)
            .into(binding.recipeImage)
    }
}