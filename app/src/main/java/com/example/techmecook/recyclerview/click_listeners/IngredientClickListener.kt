package com.example.techmecook.recyclerview.click_listeners

import com.example.techmecook.model.ingredient.IngredientGeneralInfo

interface IngredientClickListener {
    fun onClick(ingredient: IngredientGeneralInfo)
}