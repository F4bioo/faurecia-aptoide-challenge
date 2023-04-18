package com.fappslab.features.details.domain.repository

import com.fappslab.features.details.domain.model.App
import io.reactivex.Single

internal interface DetailsRepository {
    fun getApp(packageName: String): Single<App>
}
