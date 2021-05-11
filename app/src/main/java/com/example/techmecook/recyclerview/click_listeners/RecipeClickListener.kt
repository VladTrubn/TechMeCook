package com.example.techmecook.recyclerview.click_listeners

import com.example.techmecook.model.recipe.RecipeLight

interface RecipeClickListener {
    fun onClick(recipe: RecipeLight)
}