package com.example.techmecook.util

import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("bindCookingTime")
fun MaterialTextView.bindCookingTime(cookingTime: Int) {
        cookingTime?.let{
        this.text = "$cookingTime min"
    }
}