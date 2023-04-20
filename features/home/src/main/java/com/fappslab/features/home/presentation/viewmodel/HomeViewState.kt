package com.fappslab.features.home.presentation.viewmodel

import com.fappslab.features.home.domain.model.App

internal const val SUCCESS_CHILD = 0
internal const val LOADING_CHILD = 1
internal const val EMPTY_CHILD = 2

internal data class HomeViewState(
    val shouldAnimLottie: Boolean = false,
    val shouldShowRefreshing: Boolean = false,
    val flipperChild: Int = LOADING_CHILD,
    val emptyViewMessage: String? = null,
    val apps: List<App>? = null
) {

    fun onSubscribeState() = copy(
        shouldAnimLottie = false,
        shouldShowRefreshing = true,
    )
    fun getAppsFailureState(cause: Throwable) = copy(
        shouldAnimLottie = true,
        emptyViewMessage = cause.message,
        flipperChild = EMPTY_CHILD,
    )
}
