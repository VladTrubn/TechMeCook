package com.example.techmecook.paging.recipe

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.techmecook.BuildConfig
import com.example.techmecook.api.services.*
import com.example.techmecook.model.recipe.RandomRecipeCollection
import com.example.techmecook.model.recipe.RecipeGeneralInfo

class RecipeGeneralInfoDataSource(
    private val recipeService: RecipeService
) : PagingSource<Int, RecipeGeneralInfo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecipeGeneralInfo> {

        return try {
            val nextPageNumber = params.key ?: 1
            val response = loadPage(params.loadSize).recipes

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (exception: Throwable) {
            LoadResult.Error(exception)
        }
    }

    private suspend fun loadPage(
        loadSize: Int
    ): RandomRecipeCollection {
        return recipeService.getRandomRecipes(loadSize, "vegetarian", BuildConfig.SPOONACULAR_KEY,)
    }

    override fun getRefreshKey(state: PagingState<Int, RecipeGeneralInfo>): Int? {
        TODO("Not yet implemented")
    }
}

