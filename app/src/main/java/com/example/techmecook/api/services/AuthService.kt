package com.example.techmecook.api.services

import com.example.techmecook.model.login.Login
import com.example.techmecook.model.login.LoginResponse
import com.example.techmecook.model.register.Register
import com.example.techmecook.model.register.RegisterResponse
import retrofit2.http.*
import retrofit2.http.Body

interface AuthService {
    @POST("users/register")
    suspend fun register(
            @Body register: Register,
    ): RegisterResponse

    @POST("users/login")
    suspend fun login(
            @Body login: Login,
    ): LoginResponse
}