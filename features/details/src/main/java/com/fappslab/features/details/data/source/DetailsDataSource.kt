package com.fappslab.features.details.data.source

import com.fappslab.features.details.domain.model.App
import io.reactivex.Single

internal interface DetailsDataSource {
    fun getApp(packageName: String): Single<App>
}
