package com.example.techmecook.api

import com.example.techmecook.api.services.*
import com.example.techmecook.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object NetworkService {

private fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    val naiveTrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? = arrayOf()
        override fun checkClientTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) = Unit
        override fun checkServerTrusted(chain: Array<out java.security.cert.X509Certificate>?, authType: String?) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier(HostnameVerifier { _, _ -> true })
    return this
}

private fun getHTTPClient(): OkHttpClient {
    return  OkHttpClient.Builder().ignoreAllSSLErrors().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
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
val authService = createService<AuthService>()
val likeService = createService<LikeService>()

}
