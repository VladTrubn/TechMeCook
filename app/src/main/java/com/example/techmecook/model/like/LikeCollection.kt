package com.example.techmecook.model.like

import com.squareup.moshi.Json

data class LikeCollection
(
        @Json(name = "likes") val likes: List<Like>,
        @Json(name="LikedByUser") val likedByUser: Boolean
)