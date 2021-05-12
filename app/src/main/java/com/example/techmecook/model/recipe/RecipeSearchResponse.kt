package com.example.techmecook.model.recipe

import com.squareup.moshi.Json

data class RecipeSearchResponse(
    @Json(name = "results") val results: List<RecipeLight>
)