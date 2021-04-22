package com.example.techmecook.util

import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.textview.MaterialTextView

@BindingAdapter("bindCookingTime")
fun MaterialTextView.bindCookingTime(cookingTime: Int) {
        cookingTime?.let{
        this.text = "$cookingTime min"
    }
}

@BindingAdapter("bindSummary")
fun TextView.bindSummary(summary: String?) {
    summary?.let{
        this.text = Html.fromHtml(summary)
    }
}


