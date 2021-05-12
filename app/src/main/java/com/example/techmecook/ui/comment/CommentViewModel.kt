package com.example.techmecook.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.techmecook.model.comment.Comment
import com.example.techmecook.model.comment.CommentCreate
import com.example.techmecook.model.recipe.RecipeLight
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Result
import com.example.techmecook.model.result.Success
import com.example.techmecook.repositories.CommentRepository
import com.example.techmecook.repositories.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = CommentRepository()

    private val _comments = MutableLiveData<Result<List<Comment>>>()
    val comments: LiveData<Result<List<Comment>>>
        get() = _comments

    private val _createResponse = MutableLiveData<Result<Comment>>()
    val createResponse: LiveData<Result<Comment>>
        get() = _createResponse


    fun getComments(recipeId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                when (val result = repo.getComments(recipeId)) {
                    is Success -> _comments.postValue(Success(result.value.comments))
                    is Error -> {
                        Log.e("Error","${result.code} ${result.exceptionInfo}")
                        _comments.postValue(result)
                    }
                    is NetworkError -> {
                        Log.e("Error", "INTERNET ERROR")
                        _comments.postValue(result)
                    }
                }
            }
        }
    }

    fun postComment(comment: CommentCreate)
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                when (val result = repo.postComment(comment)) {
                    is Success -> _createResponse.postValue(Success(result.value))
                    is Error -> {
                        Log.e("Error","${result.code} ${result.exceptionInfo}")
                        _comments.postValue(result)
                    }
                    is NetworkError -> {
                        Log.e("Error", "INTERNET ERROR")
                        _comments.postValue(result)
                    }
                }
            }
        }
    }


}