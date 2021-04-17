package com.example.techmecook.api

import com.example.techmecook.api.services.*
import com.example.techmecook.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkService {

private fun getHTTPClient(): OkHttpClient {
    return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
}

private fun getMoshi(): Moshi {
    return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
}

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
            .client(getHTTPClient())
            .baseUrl(Constants.Spoonacular.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .build()
}

private inline fun <reified T> createService(): T =
        getRetrofit().create(T::class.java)


val recipeService = createService<RecipeService>()

}
