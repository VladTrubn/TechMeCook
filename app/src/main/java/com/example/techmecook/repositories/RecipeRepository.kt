package com.example.techmecook.repositories

import com.example.techmecook.BuildConfig
import com.example.techmecook.api.NetworkService
import com.example.techmecook.model.recipe.*
import com.example.techmecook.model.result.Result


class RecipeRepository : BaseRepository() {
    private val api = NetworkService.recipeService

  suspend fun getRandomRecipes(
        number: Int,
        tags: String?,
    ): Result<RandomRecipeCollection> {
        return coroutineApiCall(dispatcher) {
            api.getRandomRecipes(number, tags, BuildConfig.SPOONACULAR_KEY,)
        }
    }

    suspend fun getFilteredRecipes(
            query: String? = null,
            includeIngredients: String? = null,
            equipment: String? = null,
            diet: String? = null,
            type: String? = null,
            number: Int = 2,
    ): Result<RecipeSearchResponse> {
        return coroutineApiCall(dispatcher) {
            api.getFilteredRecipes(query, includeIngredients, equipment, diet, type, number)
        }
    }

    suspend fun getRecipe(
        Id: Int,
    ): Result<RecipeGeneralInfo> {
        return coroutineApiCall(dispatcher) {
            api.getRecipe(Id, BuildConfig.SPOONACULAR_KEY)
        }
    }

}

