package com.fappslab.features.home.presentation.viewmodel

internal sealed class HomeViewAction {
    object About : HomeViewAction()
    data class Details(val packageName: String) : HomeViewAction()
    data class Error(val message: String?) : HomeViewAction()
}
