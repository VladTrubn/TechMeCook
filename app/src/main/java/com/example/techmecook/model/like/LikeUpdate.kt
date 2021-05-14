package com.example.techmecook.model.like

import com.squareup.moshi.Json

data class LikeUpdate
(
        @Json(name = "CreatorId") val creatorId: String,
        @Json(name = "RecipeDbId") val recipeDbId: String
)