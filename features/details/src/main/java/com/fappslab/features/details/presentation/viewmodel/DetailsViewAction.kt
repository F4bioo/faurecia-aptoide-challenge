package com.fappslab.features.details.presentation.viewmodel

internal sealed class DetailsViewAction {
    object FinishView : DetailsViewAction()
    data class Download(val apkUrl: String, val apkName: String) : DetailsViewAction()
}
