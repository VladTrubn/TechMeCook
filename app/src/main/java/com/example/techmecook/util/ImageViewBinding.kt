package com.example.techmecook.util

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

@BindingAdapter("bindRecipeIndexImage")
fun AppCompatImageView.bindBackdropImage(imagePath: String?) {
    imagePath?.let {
        bindImage(Constants.Spoonacular.IMAGE_URL+imagePath)
    }
}
