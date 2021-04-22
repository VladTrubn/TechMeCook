package com.example.techmecook.model.ingredient

import com.squareup.moshi.Json

data class IngredientGeneralInfo (
    @Json(name = "id") val id: Int?,
    @Json(name = "image") val image: String?,
    @Json(name = "name") val name: String,
    @Json(name = "amount") val amount: String,
    @Json(name = "unit") val unit: String?,
)