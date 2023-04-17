package com.fappslab.features.home.data.source

import com.fappslab.features.home.data.api.HomeService
import com.fappslab.features.home.data.mapper.toApps
import com.fappslab.features.home.domain.model.Apps
import io.reactivex.Single

internal class HomeDataSourceImpl(
    private val service: HomeService
) : HomeDataSource {

    override fun getApps(): Single<Apps> =
        service.getApps().map { it.toApps() }
}
