package com.example.techmecook.util

import android.text.Html
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.techmecook.R

@BindingAdapter("bindImage")
fun AppCompatImageView.bindImage(imagePath: String?) {
    imagePath?.let {
        Glide.with(context)
            .load(it)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(this)
    }
}

@BindingAdapter("bindRecipeImage")
fun AppCompatImageView.bindRecipeImage(image: String?) {
    image?.let {
        bindImage(image)
    }
}

@BindingAdapter("bindIngredientInrecipeImage")
fun AppCompatImageView.bindIngredientInrecipeImage(image: String?) {
    image?.let {
        bindImage(Constants.Spoonacular.IMAGE_URL + image)
    }
}


