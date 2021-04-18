package com.example.techmecook.model.result

import com.example.techmecook.model.error.ErrorResponse

sealed class Result<out T>

data class Success<out T>(val value: T) : Result<T>()
data class Error(val code: Int? = null, val error: ErrorResponse? = null, val exceptionInfo: String? = null) : Result<Nothing>()
object NetworkError : Result<Nothing>()

