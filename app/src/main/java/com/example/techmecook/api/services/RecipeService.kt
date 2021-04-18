package com.example.techmecook.api.services

import com.example.techmecook.model.recipe.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("recipes/random")
   suspend fun getRandomRecipes(
            @Query("apiKey") apiKey: String,
            @Query("number") number: Int,
            @Query("tags") tags: String?,
    ): RandomRecipeCollection

}

