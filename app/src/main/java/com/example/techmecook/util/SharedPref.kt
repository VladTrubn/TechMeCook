package com.example.techmecook.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SharedPref {

    private const val API_PREF_NAME = "ApiPreferences"
    private const val CONST_API_TOKEN = "API_TOKEN"


    private fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            API_PREF_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun writeApiToken(context: Context, token: String) {
        getEncryptedSharedPreferences(context).edit {
            putString(CONST_API_TOKEN, token)
        }
        Log.e("TOKEN", "Written token: $token")
    }

    fun getApiToken(context: Context): String {
        val token = getEncryptedSharedPreferences(context).getString(CONST_API_TOKEN, "") ?: ""
        Log.e("TOKEN","Retrieved token: $token")
        return token
    }


}