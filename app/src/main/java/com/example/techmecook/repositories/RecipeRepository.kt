package com.example.techmecook.repositories

import com.example.techmecook.api.NetworkService
import com.example.techmecook.model.*
import com.example.techmecook.model.recipe.RecipeGeneralInfo
import com.example.techmecook.model.result.Result


class RecipeRepository : BaseRepository() {
    private val api = NetworkService.recipeService

  suspend  fun getRandomRecipes(
        apiKey: String,
        number: Int,
        tags: String?,
    ): Result<RecipeGeneralInfo> {
        return coroutineApiCall(dispatcher) {
            api.getRandomRecipes(apiKey, number, tags)
        }
    }

}