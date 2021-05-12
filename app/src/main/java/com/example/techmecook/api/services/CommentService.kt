package com.example.techmecook.api.services

import com.example.techmecook.model.comment.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface  CommentService {

    @GET("comments/get")
    suspend fun getComments(
            @Query("recipeId") recipeId: Int
    ): CommentCollection


}

