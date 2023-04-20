package com.fappslab.features.details.data.repository

import com.fappslab.features.details.data.source.DetailsDataSource
import com.fappslab.features.details.domain.model.App
import com.fappslab.features.details.domain.repository.DetailsRepository
import io.reactivex.Single

internal class DetailsRepositoryImpl(
    private val dataSource: DetailsDataSource
) : DetailsRepository {

    override fun getApp(packageName: String): Single<App> =
        dataSource.getApp(packageName)
}
