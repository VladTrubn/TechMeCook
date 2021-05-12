package com.example.techmecook.repositories

import com.example.techmecook.api.NetworkService
import com.example.techmecook.model.comment.*
import com.example.techmecook.model.result.*

class CommentRepository : BaseRepository() {
    private val api = NetworkService.commentService

    suspend fun getComments(
            recipeId: Int
    ): Result<CommentCollection> {
        return coroutineApiCall(dispatcher) {
            api.getComments(recipeId)
        }
    }

}

