package com.fappslab.libraries.design.extension

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import coil.load
import coil.request.CachePolicy
import com.fappslab.aptoide.libraries.design.R

fun FragmentActivity.setStatusBarTransparent() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

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
