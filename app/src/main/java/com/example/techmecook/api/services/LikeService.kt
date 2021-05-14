package com.example.techmecook.api.services

import com.example.techmecook.model.like.LikeCollection
import com.example.techmecook.model.like.LikeUpdate
import retrofit2.http.*

interface  LikeService {

    @GET("likes/get")
    suspend fun getLikes(
            @Query("recipeId") recipeId: Int,
            @Query("creatorId") creatorId: String
    ): LikeCollection

    @POST("likes/update")
    suspend fun updateLike(
            @Body like: LikeUpdate
    ): LikeCollection




}

