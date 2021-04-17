package com.example.techmecook.api

import com.example.techmecook.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
                .build()

        return chain.proceed(request)
    }
}