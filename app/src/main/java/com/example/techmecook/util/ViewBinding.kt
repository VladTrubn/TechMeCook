package com.example.techmecook.util

import android.graphics.Color
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("bindColor")
fun AppCompatImageView.bindColor(color: String?) {
    color?.let {
        this.setBackgroundColor(Color.parseColor(it))
    }
}

@BindingAdapter("bindErrorText")
fun TextInputLayout.bindErrorText(error: String?) {
    this.error = error
}

@BindingAdapter("bindText")
fun AutoCompleteTextView.bindText(text: String?) {
    text?.let {
        this.setText(it, false)
    }
}