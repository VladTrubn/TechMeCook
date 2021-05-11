package com.example.techmecook.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.techmecook.model.recipe.RecipeLight
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Result
import com.example.techmecook.model.result.Success
import com.example.techmecook.repositories.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = RecipeRepository()

    private val _recipes = MutableLiveData<Result<List<RecipeLight>>>()
    val recipes: LiveData<Result<List<RecipeLight>>>
        get() = _recipes


    fun getRandomRecipes(qty: Int = 2) {
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                when (val result = repo.getRandomRecipes(qty, null)) {
                    is Success -> _recipes.postValue(Success(result.value.recipes))
                    is Error -> {
                        Log.e("Error","${result.code} ${result.exceptionInfo}")
                        _recipes.postValue(result)
                    }
                    is NetworkError -> {
                        Log.e("Error", "INTERNET ERROR")
                        _recipes.postValue(result)
                    }
                }
            }
        }
    }

}