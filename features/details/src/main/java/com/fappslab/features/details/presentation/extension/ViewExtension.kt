package com.fappslab.features.details.presentation.extension

import com.airbnb.lottie.LottieAnimationView
import com.facebook.shimmer.ShimmerFrameLayout

internal fun ShimmerFrameLayout.animHandle(shouldAnim: Boolean) {
    if (shouldAnim) {
        startShimmer()
    } else stopShimmer()
}

internal fun LottieAnimationView.animHandle(shouldAnim: Boolean) {
    if (shouldAnim) {
        resumeAnimation()
    } else pauseAnimation()
}
