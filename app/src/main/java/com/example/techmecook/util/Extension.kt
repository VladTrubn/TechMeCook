package com.example.techmecook.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LiveData
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.*

fun Context.showShortText(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.writeToken(token: String) {
    SharedPref.writeApiToken(this, token)
}

fun Context.getToken(): String {
    return SharedPref.getApiToken(this)
}

fun String.isMail(): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(
            this
    ).matches()
}

fun TextInputEditText.invalidateError(layout: TextInputLayout) {
    this.doAfterTextChanged {
        layout.invalidateError()
    }
}


fun TextInputLayout.invalidateError() {
    if (this.error != null)
        this.error = null
}

fun <T> LiveData<T>.getNonNullValue() =
        requireNotNull(this.value)

fun Context.hideSoftKeyboard(activity : FragmentActivity) {
    if (activity.getCurrentFocus() == null){
        return
    }
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(activity?.getCurrentFocus()?.getWindowToken(), 0)
}
