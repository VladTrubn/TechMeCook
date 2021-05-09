package com.example.techmecook.repositories

import android.content.Context
import com.example.techmecook.api.NetworkService
import com.example.techmecook.model.login.Login
import com.example.techmecook.model.login.LoginResponse
import com.example.techmecook.model.result.Result
import com.example.techmecook.model.register.Register
import com.example.techmecook.model.register.RegisterResponse

class AuthRepository() : BaseRepository() {
    private val api = NetworkService.authService

    suspend fun register(
            register: Register
    ): Result<RegisterResponse> {
        return coroutineApiCall(dispatcher) {
            api.register(register)
        }
    }

    suspend fun login(
            login: Login
    ): Result<LoginResponse> {
        return coroutineApiCall(dispatcher) {
            api.login(login)
        }
    }

}