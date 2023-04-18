package com.fappslab.features.details.presentation.viewmodel

import androidx.annotation.ColorRes
import com.fappslab.features.details.domain.model.App
import com.fappslab.aptoide.libraries.design.R as DS

private const val TRUSTED = "TRUSTED"
internal const val SUCCESS_CHILD = 0
internal const val LOADING_CHILD = 1
internal const val EMPTY_CHILD = 2

internal data class DetailsViewState(
    val flipperChild: Int = LOADING_CHILD,
    val screenshots: List<String>? = null,
    val errorMessage: String? = null,
    val app: App? = null,
    @ColorRes val rankColor: Int = DS.color.ds_green
) {

    fun getAppState(app: App) = copy(
        rankColor = app.apk.malware.rank.toRankColor(),
        screenshots = app.content.screenshots,
        flipperChild = SUCCESS_CHILD,
        app = app
    )

    private fun String.toRankColor() =
        if (this == TRUSTED) {
            DS.color.ds_green
        } else DS.color.ds_red
}
