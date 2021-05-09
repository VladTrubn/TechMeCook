package com.example.techmecook.model.login

import com.squareup.moshi.Json

data class LoginResponse(
        @Json(name = "email") val email: String,
        @Json(name = "userName") val username: String,
        @Json(name = "passwordHash") val passwordHash: String
)