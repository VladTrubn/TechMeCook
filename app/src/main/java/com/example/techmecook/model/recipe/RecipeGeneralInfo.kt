package com.example.techmecook.model.recipe

import com.squareup.moshi.Json

data class RecipeGeneralInfo (

    @Json(name = "id") val id: Int,
    @Json(name = "spoonacularSourceUrl") val spoonacularSourceUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "summary") val summary: String?,
    @Json(name = "readyInMinutes") val readyInMinutes: Int,

)