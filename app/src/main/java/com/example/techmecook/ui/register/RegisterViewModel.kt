package com.example.techmecook.ui.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.techmecook.R
import com.example.techmecook.model.register.Register
import com.example.techmecook.model.register.RegisterResponse
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

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepo = AuthRepository()

    private val _registerResponse = MutableLiveData<Result<RegisterResponse>>()
    val registerResponse: LiveData<Result<RegisterResponse>>
        get() = _registerResponse

    val email = MutableLiveData("")
    val userName = MutableLiveData("")
    val password = MutableLiveData("")
    val passwordRepeat = MutableLiveData("")

    val emailError = MutableLiveData("")
    val userNameError = MutableLiveData("")
    val passwordError = MutableLiveData("")
    val passwordRepeatError = MutableLiveData("")

    private fun register() {
        val data =
            Register(
                email.getNonNullValue(),
                userName.getNonNullValue(),
                password.getNonNullValue()
            )

        viewModelScope.launch {
            withContext(Dispatchers.IO + viewModelScope.coroutineContext) {
                val result = authRepo.register(data)
                _registerResponse.postValue(result)

                when (result) {
                    is Success -> registerSuccessful(result.value)
                    is Error -> {
                        Log.e("REGISTER ERROR","${result.code} ${result.exceptionInfo}")
                        registerUnsuccessful()
                    }
                    is NetworkError -> {
                        Log.e("REGISTER ERROR","INTERNET ERROR")
                        registerUnsuccessful()
                    }
                }
            }
        }
    }

    fun tryRegister() {
        if (validate()) register()
    }

    private fun validate(): Boolean {
        val mailValidation = validateEmail(email.getNonNullValue())
        val userNameValidation = validateUserName(userName.getNonNullValue())
        val passValidation = validatePass(password.getNonNullValue())
        val passRepeatValidation =
            validatePassRepeat(password.getNonNullValue(), passwordRepeat.getNonNullValue())

        emailError.postValue(mailValidation)
        userNameError.postValue(userNameValidation)
        passwordError.postValue(passValidation)
        passwordRepeatError.postValue(passRepeatValidation)

        return mailValidation.isEmpty() && userNameValidation.isEmpty()
                && passValidation.isEmpty() && passRepeatValidation.isEmpty()
    }

    private fun validateEmail(mail: String): String {
        if (mail.isEmpty() || mail.isBlank()) {
            return getApplication<Application>().applicationContext.getString(R.string.mail_cannot_be_empty)
        } else if (!mail.isMail()) {
            return getApplication<Application>().applicationContext.getString(R.string.mail_is_not_valid)
        }
        return ""
    }

    private fun validateUserName(username: String): String {
        if (username.isEmpty() || username.isBlank()) {
            return getApplication<Application>().applicationContext.getString(R.string.username_cannot_be_empty)
        }
        return ""
    }

    private fun validatePass(pass: String): String {
        if (pass.isEmpty() || pass.isBlank()) {
            return getApplication<Application>().applicationContext.getString(R.string.password_cannot_be_empty)
        }
        return ""
    }

    private fun validatePassRepeat(pass: String, passRepeat: String): String {
        if (pass != passRepeat) {
            return getApplication<Application>().applicationContext.getString(R.string.passwords_should_be_same)
        }
        return ""
    }

    private fun registerSuccessful(registerResponse: RegisterResponse) {
        getApplication<Application>().applicationContext.writeToken(registerResponse.username)
    }

    private fun registerUnsuccessful() {
        getApplication<Application>().applicationContext.writeToken("")
    }
}