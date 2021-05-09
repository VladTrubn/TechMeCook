package com.example.techmecook.ui.recipe_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmecook.model.login.Login
import com.example.techmecook.model.login.LoginResponse
import com.example.techmecook.model.recipe.*
import com.example.techmecook.model.register.Register
import com.example.techmecook.model.register.RegisterResponse
import com.example.techmecook.model.result.*
import com.example.techmecook.repositories.AuthRepository
import com.example.techmecook.repositories.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {
    private val repo = RecipeRepository()
    private val userRepo = AuthRepository()
    fun getRecipe(Id: Int): LiveData<RecipeGeneralInfo> {
        val recipe = MutableLiveData<RecipeGeneralInfo>()

        viewModelScope.launch {
            when (val result = getRecipeFromRepo(Id)) {
                is Success -> recipe.postValue(result.value!!)
                is Error -> Log.e("ERROR IN ACTIVITY", "${result.exceptionInfo}")
                is NetworkError ->  Log.e("ERROR IN ACTIVITY", "INTERNET ERROR")
            }
        }

        return recipe
    }

    private suspend fun getRecipeFromRepo(Id: Int) =
        repo.getRecipe(Id)



    //LOGIN AND REGISTER
    //////////////////////////////////////
    fun register(register: Register): LiveData<RegisterResponse> {
        val registerResponse = MutableLiveData<RegisterResponse>()
        viewModelScope.launch {
            when (val result = sendRegisterRequest(register)) {
                is Success -> {
                    Log.e("SUCCESS!!", "${result.value.email} ${result.value.id} ${result.value.username} ")
                }
                is Error -> Log.e("ERROR IN ACTIVITY", "${result.code} ${result.exceptionInfo}")
                is NetworkError ->  Log.e("ERROR IN ACTIVITY", "INTERNET ERROR")
            }
        }
        return registerResponse
    }
    private suspend fun sendRegisterRequest(register: Register) =
            userRepo.register(register)

    fun login(login: Login): LiveData<LoginResponse> {
        val registerResponse = MutableLiveData<LoginResponse>()
        viewModelScope.launch {
            when (val result = sendLoginRequest(login)) {
                is Success -> {
                    Log.e("SUCCESS!!", "${result.value.email} ${result.value.username} ${result.value.passwordHash} ")
                }
                is Error -> Log.e("ERROR IN ACTIVITY", "${result.code} ${result.exceptionInfo}")
                is NetworkError ->  Log.e("ERROR IN ACTIVITY", "INTERNET ERROR")
            }
        }
        return registerResponse
    }
    private suspend fun sendLoginRequest(login: Login) =
            userRepo.login(login)
    //////////////////////////////////////
}



