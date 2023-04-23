package com.fappslab.libraries.design.extension

import android.content.Context
import android.content.res.Configuration
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import coil.load
import coil.request.CachePolicy
import com.fappslab.aptoide.libraries.design.R

fun ImageView.loadImage(urlImage: String) {
    load(urlImage) {
        crossfade(enable = true)
        placeholder(R.drawable.ds_anim_loading)
        error(R.drawable.ds_illu_waiting)
        memoryCachePolicy(CachePolicy.ENABLED)
    }
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    setColorFilter(ContextCompat.getColor(context, colorRes))
}

fun Context.getColorRes(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}

fun Context.isDarkModeActivated(): Boolean {
    return resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES
}
