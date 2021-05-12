package com.example.techmecook.model.comment

import com.squareup.moshi.Json

data class CommentCollection
(
        @Json(name = "comments") val comments: List<Comment>
)