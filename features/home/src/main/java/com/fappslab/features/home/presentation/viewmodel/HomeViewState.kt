package com.fappslab.features.home.presentation.viewmodel

import com.fappslab.features.home.domain.model.App

internal const val SUCCESS_CHILD = 0
internal const val LOADING_CHILD = 1
internal const val EMPTY_CHILD = 2

internal data class HomeViewState(
    val shouldShowRefreshing: Boolean = false,
    val flipperChild: Int = LOADING_CHILD,
    val emptyViewMessage: String? = null,
    val apps: List<App>? = null
)
