package com.fappslab.features.home.data.repository

import com.fappslab.features.home.data.source.HomeDataSource
import com.fappslab.features.home.domain.model.Apps
import com.fappslab.features.home.domain.repository.HomeRepository
import io.reactivex.Single

internal class HomeRepositoryImpl(
    private val dataSource: HomeDataSource
) : HomeRepository {

    override fun getApps(): Single<Apps> =
        dataSource.getApps()
}
