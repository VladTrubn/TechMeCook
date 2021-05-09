package com.example.techmecook.ui.login

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.techmecook.R
import com.example.techmecook.model.login.Login
import com.example.techmecook.model.login.LoginResponse
import com.example.techmecook.model.result.Error
import com.example.techmecook.model.result.NetworkError
import com.example.techmecook.model.result.Result
import com.example.techmecook.model.result.Success
import com.example.techmecook.repositories.AuthRepository
import com.example.techmecook.util.getNonNullValue
import com.example.techmecook.util.isMail
import com.example.techmecook.util.writeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepo = AuthRepository()

    private val _loginResponse = MutableLiveData<Result<LoginResponse>>()
    val loginResponse: LiveData<Result<LoginResponse>>
        get() = _loginResponse

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    val emailError = MutableLiveData("")
    val passwordError = MutableLiveData("")

    private fun login() {
        val data = Login(email.getNonNullValue(), password.getNonNullValue())

        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                val result = authRepo.login(data)
                _loginResponse.postValue(result)
                when (result) {
                    is Success -> loginSuccessful(result.value)
                    is Error -> {
                        Log.e("ERROR","${result.code} ${result.exceptionInfo}")
                        loginUnsuccessful()
                    }
                    is NetworkError -> {
                        Log.e("ERROR","INTERNET ERROR")
                        loginUnsuccessful()
                    }
                }
            }
        }
    }

    fun tryLogin() {
        if (validate()) login()
    }

    private fun validate(): Boolean {
        val mailValidation = validateEmail(email.getNonNullValue())
        val passValidation = validatePass(password.getNonNullValue())

        emailError.postValue(mailValidation)
        passwordError.postValue(passValidation)

        return mailValidation.isEmpty() && passValidation.isEmpty()
    }

    private fun validateEmail(mail: String): String {
        if (mail.isEmpty() || mail.isBlank()) {
            return getApplication<Application>().applicationContext.getString(R.string.mail_cannot_be_empty)
        } else if (!mail.isMail()) {
            return getApplication<Application>().applicationContext.getString(R.string.mail_is_not_valid)
        }

        return ""
    }

    private fun validatePass(pass: String): String {
        if (pass.isEmpty() || pass.isBlank()) {
            return getApplication<Application>().applicationContext.getString(R.string.password_cannot_be_empty)
        }

        return ""
    }

    private fun loginSuccessful(loginResponse: LoginResponse) {
        getApplication<Application>().applicationContext.writeToken(loginResponse.username)
    }

    private fun loginUnsuccessful() {
        getApplication<Application>().applicationContext.writeToken("")
    }

}

