package com.example.techmecook.model.recipe

import com.squareup.moshi.Json

data class RandomRecipeCollection(
    @Json(name = "recipes") val recipes: List<RecipeLight>
)

