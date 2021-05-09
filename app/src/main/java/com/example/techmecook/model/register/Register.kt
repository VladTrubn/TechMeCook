package com.example.techmecook.model.register

import com.squareup.moshi.Json

data class Register(
        @Json(name = "email") val email: String,
        @Json(name = "username") val username: String,
        @Json(name = "password") val password: String,
)