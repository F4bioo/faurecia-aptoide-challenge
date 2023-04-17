package com.fappslab.features.home.presentation.viewmodel

internal sealed class HomeViewAction {
    data class Details(val packageName: String) : HomeViewAction()
    data class About(val shouldShow: Boolean) : HomeViewAction()
    data class Error(val shouldShow: Boolean, val message: String?) : HomeViewAction()
}
