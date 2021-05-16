package com.example.techmecook.repositories

import com.example.techmecook.api.NetworkService
import com.example.techmecook.model.like.LikeCollection
import com.example.techmecook.model.like.LikeUpdate
import com.example.techmecook.model.result.*

class LikeRepository : BaseRepository() {
    private val api = NetworkService.likeService

    suspend fun getLikes(
            recipeId: Int,
            creatorId: String
    ): Result<LikeCollection> {
        return coroutineApiCall(dispatcher) {
            api.getLikes(recipeId, creatorId)
        }
    }

    suspend fun updateLike(
            like: LikeUpdate
    ): Result<LikeCollection> {
        return coroutineApiCall(dispatcher) {
            api.updateLike(like)
        }
    }

}

