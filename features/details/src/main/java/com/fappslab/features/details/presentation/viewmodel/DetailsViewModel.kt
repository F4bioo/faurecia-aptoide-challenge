package com.fappslab.features.details.presentation.viewmodel

import com.fappslab.features.details.domain.model.App
import com.fappslab.features.details.domain.usecase.GetAppUseCase
import com.fappslab.libraries.arch.extension.schedulerOn
import com.fappslab.libraries.arch.viewmodel.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

internal class DetailsViewModel(
    private val packageName: String,
    private val getAppUseCase: GetAppUseCase,
    private val scheduler: Scheduler = AndroidSchedulers.mainThread()
) : ViewModel<DetailsViewState, DetailsViewAction>(DetailsViewState()) {

    init {
        getApp()
    }

    private fun getApp() {
        getAppUseCase(packageName)
            .schedulerOn(scheduler)
            .doOnSubscribe { onState { it.copy(flipperChild = LOADING_CHILD) } }
            .subscribe(::getAppSuccess, ::getAppFailure)
            .disposableHandler()
    }

    private fun getAppSuccess(app: App) {
        onState { it.getAppState(app) }
    }

    private fun getAppFailure(cause: Throwable) {
        onState { it.copy(errorMessage = cause.message, flipperChild = EMPTY_CHILD) }
    }

    fun onBack() {
        onAction { DetailsViewAction.FinishView }
    }

    fun onTryAgain() {
        getApp()
    }

    fun onDownload() {
        val (apkUrl, apkName) = state.value.app.run { this?.apk?.path to this?.name }
        onAction { DetailsViewAction.Download(apkUrl.orEmpty(), apkName.orEmpty()) }
    }
}
