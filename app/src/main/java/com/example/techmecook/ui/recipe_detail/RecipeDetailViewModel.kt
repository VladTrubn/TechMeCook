package com.example.techmecook.ui.recipe_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techmecook.model.recipe.*
import com.example.techmecook.model.result.*
import com.example.techmecook.repositories.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel : ViewModel() {
    private val repo = RecipeRepository()

    fun getRecipe(Id: Int): LiveData<RecipeGeneralInfo> {
        val recipe = MutableLiveData<RecipeGeneralInfo>()

        viewModelScope.launch {
            when (val result = getRecipeFromRepo(Id)) {
                is Success -> recipe.postValue(result.value!!)
            }
        }

        return recipe
    }

    private suspend fun getRecipeFromRepo(Id: Int) =
        repo.getRecipe(Id)
}

