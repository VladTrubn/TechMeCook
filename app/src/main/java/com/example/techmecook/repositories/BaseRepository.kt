package com.example.techmecook.repositories

import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import com.example.techmecook.model.result.*
import com.example.techmecook.model.error.ErrorResponse

open class BaseRepository {
    suspend fun <T> coroutineApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            try {
               Success(apiCall.invoke())
            } catch (exception: IOException) {
                Error (-1, null, exception.message)
            } catch (exception: HttpException) {
                Error(exception.code(), throwableToErrorResponse(exception))
            } catch (throwable: Throwable) {
                Error(-1, null, throwable.toString())
            }
        }
    }

    open val dispatcher = Dispatchers.IO

    private fun throwableToErrorResponse(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Throwable) {
            null
        }
    }
}

