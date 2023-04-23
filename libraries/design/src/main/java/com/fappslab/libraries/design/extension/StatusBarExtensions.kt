package com.fappslab.libraries.design.extension

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.fappslab.aptoide.libraries.design.R
import com.fappslab.libraries.arch.extension.isNotNull

fun FragmentActivity.setStatusBarColor(@ColorRes colorRes: Int = R.color.ds_primary) = window.run {
    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    statusBarColor = getColorRes(colorRes)
}

fun FragmentActivity.setTransparentStatusBar() = window.run {
    addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    statusBarColor = Color.TRANSPARENT

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        decorView.windowInsetsController?.run {
            statusBarIconColorHandle(context = this@setTransparentStatusBar)
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else @Suppress("DEPRECATION") {
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}

fun FragmentActivity.setNavigationBarBackgroundColor(@ColorRes colorRes: Int) {
    window.navigationBarColor = getColorRes(colorRes)
}

fun FragmentActivity.setTransparentNavigationBar() {
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    window.navigationBarColor = Color.TRANSPARENT
}

fun FragmentActivity.setLightNavigationBarIcons() {
    val isLight = isDarkModeActivated()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val controller = window.decorView.windowInsetsController
        if (controller.isNotNull()) {
            if (isLight.not()) {
                controller?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            } else {
                controller?.setSystemBarsAppearance(
                    0, WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                )
            }
        }
    } else @Suppress("DEPRECATION") {
        val decorView = window.decorView
        var systemUiVisibilityFlags = decorView.systemUiVisibility
        systemUiVisibilityFlags = if (isLight.not()) {
            systemUiVisibilityFlags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        decorView.systemUiVisibility = systemUiVisibilityFlags
    }
}

@RequiresApi(Build.VERSION_CODES.R)
private fun WindowInsetsController.statusBarIconColorHandle(context: FragmentActivity) {
    if (context.isDarkModeActivated()) {
        setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS)
    } else setSystemBarsAppearance(
        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
    )
}
