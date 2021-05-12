package com.example.techmecook.model.comment

import com.squareup.moshi.Json

data class CommentCreate
(
        @Json(name="Text") val text: String,
        @Json(name = "CreatorId") val creatorId: String,
        @Json(name = "RecipeDbId") val recipeDbId: String

)