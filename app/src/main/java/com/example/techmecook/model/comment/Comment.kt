package com.example.techmecook.model.comment

import com.squareup.moshi.Json

data class Comment
(
        @Json(name="Id") val id: String?,
        @Json(name = "Text") val text: String?,
        @Json(name = "Created") val created: String?,
        @Json(name = "CreatorName") val creatorName: String?

)