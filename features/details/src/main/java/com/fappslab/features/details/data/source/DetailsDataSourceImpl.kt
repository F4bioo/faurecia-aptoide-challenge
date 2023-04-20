package com.fappslab.features.details.data.source

import com.fappslab.features.details.data.api.DetailsService
import com.fappslab.features.details.data.mapper.toApp
import com.fappslab.features.details.domain.model.App
import com.fappslab.libraries.arch.extension.parseHttpError
import io.reactivex.Single

internal class DetailsDataSourceImpl(
    private val service: DetailsService
) : DetailsDataSource {

    override fun getApp(packageName: String): Single<App> =
        service.getApp(packageName)
            .map { it.toApp() }
            .parseHttpError()
}
