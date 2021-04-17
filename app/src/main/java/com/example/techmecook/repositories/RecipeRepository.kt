package com.example.techmecook.repositories

import com.example.techmecook.BuildConfig
import com.example.techmecook.api.NetworkService
import com.example.techmecook.model.recipe.*
import com.example.techmecook.model.result.Result


class RecipeRepository : BaseRepository() {
    private val api = NetworkService.recipeService

  suspend  fun getRandomRecipes(
        number: Int,
        tags: String?,
    ): Result<RandomRecipeCollection> {
        return coroutineApiCall(dispatcher) {
            api.getRandomRecipes(BuildConfig.SPOONACULAR_KEY, number, tags)
        }
    }

}