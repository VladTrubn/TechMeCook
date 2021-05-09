package com.example.techmecook.model.register

import com.squareup.moshi.Json

data class RegisterResponse(
        @Json(name = "email") val email: String,
        @Json(name = "userName") val username: String,
        @Json(name = "id") val id: String
)