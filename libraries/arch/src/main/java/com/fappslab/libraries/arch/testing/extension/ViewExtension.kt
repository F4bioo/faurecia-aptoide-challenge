package com.fappslab.libraries.arch.testing.extension

import androidx.annotation.IdRes
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun Fragment.cancelLottieAnimation(@IdRes lottieIdRes: Int) {
    val lottie = view?.findViewById<LottieAnimationView>(lottieIdRes)
    lottie?.cancelAnimation()
}
