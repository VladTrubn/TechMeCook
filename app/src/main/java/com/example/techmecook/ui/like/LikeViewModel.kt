package com.example.techmecook.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.techmecook.model.like.LikeUpdate
import com.example.techmecook.model.like.*
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Result
import com.example.techmecook.model.result.Success
import com.example.techmecook.repositories.LikeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LikeViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = LikeRepository()

    private val _likes = MutableLiveData<Result<LikeCollection>>()
    val likes: LiveData<Result<LikeCollection>>
        get() = _likes

    private val _updateResponse = MutableLiveData<Result<LikeCollection>>()
    val updateResponse: LiveData<Result<LikeCollection>>
        get() = _updateResponse


    fun getLikes(recipeId: Int, creatorId: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                when (val result = repo.getLikes(recipeId, creatorId)) {
                    is Success -> _likes.postValue(Success(result.value))
                    is Error -> {
                        Log.e("Error", "${result.code} ${result.exceptionInfo}")
                        _likes.postValue(result)
                    }
                    is NetworkError -> {
                        Log.e("Error", "INTERNET ERROR")
                        _likes.postValue(result)
                    }
                }
            }
        }
    }

    fun updateLike(like: LikeUpdate) {
        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                _likes.postValue(repo.updateLike(like))
            }
        }
    }


}