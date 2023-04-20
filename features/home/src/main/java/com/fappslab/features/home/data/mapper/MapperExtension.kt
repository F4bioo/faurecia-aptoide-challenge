package com.fappslab.features.home.data.mapper

import com.fappslab.features.home.data.model.AppsResponse
import com.fappslab.features.home.data.model.AppsResponse.Responses.ListAppsResponse.DatasetsResponse.AllResponse.DataResponse.AppResponse
import com.fappslab.features.home.domain.model.App
import com.fappslab.features.home.domain.model.Apps
import com.fappslab.libraries.arch.extension.orZero

internal fun AppsResponse?.toApps() =
    Apps(
        list = this?.responses?.listApps?.datasets?.all?.data?.list?.map {
            it.toApp()
        }.orEmpty()
    )

internal fun AppResponse.toApp() =
    App(
        id = id.orZero(),
        name = name.orEmpty(),
        packageName = packageName.orEmpty(),
        versionCode = versionCode.orZero(),
        downloads = downloads.orZero(),
        graphic = graphic.orEmpty(),
        icon = graphic.orEmpty(),
        storeName = storeName.orEmpty()
    )
