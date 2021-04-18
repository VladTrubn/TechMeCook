package com.example.techmecook.model.recipe

import com.example.techmecook.model.ingredient.IngredientGeneralInfo
import com.squareup.moshi.Json
import com.example.techmecook.model.instruction.Instruction

data class RecipeGeneralInfo (

    @Json(name = "id") val id: Int,
    @Json(name = "spoonacularSourceUrl") val spoonacularSourceUrl: String?,
    @Json(name = "title") val title: String,
    @Json(name = "summary") val summary: String,
    @Json(name = "readyInMinutes") val readyInMinutes: Int,
    @Json(name = "image") val image: String?,
    @Json(name="analyzedInstructions") val analyzedInstructions: List<Instruction>,
    @Json(name="extendedIngredients") val extendedIngredients: List<IngredientGeneralInfo>

)