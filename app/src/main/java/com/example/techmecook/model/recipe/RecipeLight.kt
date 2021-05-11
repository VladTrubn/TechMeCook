package com.example.techmecook.model.recipe

import com.squareup.moshi.Json

data class RecipeLight(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "image") val image: String
)