package com.fappslab.features.home.presentation.viewmodel

import androidx.annotation.IdRes
import com.fappslab.aptoide.features.home.R
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.domain.model.Apps
import com.fappslab.features.home.domain.usecase.GetAppsUseCase
import com.fappslab.libraries.arch.extension.schedulerOn
import com.fappslab.libraries.arch.viewmodel.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

internal class HomeViewModel(
    private val getAppsUseCase: GetAppsUseCase,
    private val scheduler: Scheduler = AndroidSchedulers.mainThread()
) : ViewModel<HomeViewState, HomeViewAction>(HomeViewState()) {

    init {
        getApps()
    }

    private fun getApps() {
        getAppsUseCase()
            .schedulerOn(scheduler)
            .doOnSubscribe { onState { it.onSubscribeState() } }
            .doAfterTerminate { onState { it.copy(shouldShowRefreshing = false) } }
            .subscribe(::getAppsSuccess, ::getAppsFailure)
            .disposableHandler()
    }

    private fun getAppsSuccess(apps: Apps) {
        onState { it.copy(apps = apps.list) }
    }

    private fun getAppsFailure(cause: Throwable) {
        if (state.value.apps.isNullOrEmpty()) {
            onState { it.getAppsFailureState(cause) }
        } else onAction { HomeViewAction.Error(shouldShow = true, message = cause.message) }
    }

    fun onClickItem(app: App) {
        onAction { HomeViewAction.Details(app.packageName) }
    }

    fun onSubmitList() {
        onState { it.copy(flipperChild = SUCCESS_CHILD) }
    }

    fun onRefresh() {
        getApps()
    }

    fun onErrorDismiss() {
        onAction { HomeViewAction.Error(shouldShow = false, message = null) }
    }

    fun onAboutDismiss() {
        onAction { HomeViewAction.About(shouldShow = false) }
    }

    fun onEmptyButtonClicked() {
        onState { it.copy(flipperChild = LOADING_CHILD) }
        getApps()
    }

    fun onMenuClicked(@IdRes idRes: Int) {
        when (idRes) {
            R.id.about -> onAction { HomeViewAction.About(shouldShow = true) }
        }
    }
}
