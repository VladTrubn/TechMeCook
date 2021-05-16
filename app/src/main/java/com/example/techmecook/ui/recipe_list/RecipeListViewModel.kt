package com.example.techmecook.ui.recipe_list

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


    fun getRandomRecipes(qty: Int = 3) {
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

    fun getFilteredRecipes(
            query: String?,
            includeIngredients: String?,
            equipment: String?,
            diet: String?,
            type: String?
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                when (val result =
                        repo.getFilteredRecipes(query, includeIngredients, equipment, diet, type)) {
                    is Success -> _recipes.postValue(Success(result.value.results))
                    is Error -> {
                        Log.e("ERROR","${result.code} ${result.exceptionInfo}")
                        _recipes.postValue(result)
                    }
                    is NetworkError -> {
                        Log.e("ERROR","INTERNET ERROR")
                        _recipes.postValue(result)
                    }
                }
            }
        }
    }

}