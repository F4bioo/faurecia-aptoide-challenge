package com.fappslab.libraries.arch.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

private fun RequestBuilder<Drawable>.diskCacheStrategy(): RequestBuilder<Drawable> {
    return priority(Priority.HIGH)
        .transition(DrawableTransitionOptions.withCrossFade())
        .diskCacheStrategy(DiskCacheStrategy.ALL)
}

fun ImageView.load(params: ImageLoadParams) {
    Glide.with(context)
        .load(params.imageUrl)
        .placeholder(params.placeholderRes)
        .error(params.errorRes)
        .diskCacheStrategy()
        .into(this)
}

data class ImageLoadParams(
    val imageUrl: String,
    @DrawableRes val placeholderRes: Int,
    @DrawableRes val errorRes: Int
)
