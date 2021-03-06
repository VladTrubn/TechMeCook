package com.example.techmecook.model.error

import com.squareup.moshi.Json

data class ErrorResponse(
        @Json(name = "status_message") val statusMessage: String?,
        @Json(name = "status_code") val statusCode: Int?
)