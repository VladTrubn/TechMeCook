package com.example.techmecook.api.services

import com.example.techmecook.model.comment.*
import com.example.techmecook.model.register.Register
import com.example.techmecook.model.register.RegisterResponse
import retrofit2.http.*

interface  CommentService {

    @GET("comments/get")
    suspend fun getComments(
            @Query("recipeId") recipeId: Int
    ): CommentCollection

    @POST("comments/create")
    suspend fun postComment(
            @Body comment: CommentCreate
    ): Comment


}

