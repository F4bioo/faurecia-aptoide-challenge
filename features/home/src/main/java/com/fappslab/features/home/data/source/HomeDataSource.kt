package com.fappslab.features.home.data.source

import com.fappslab.features.home.domain.model.Apps
import io.reactivex.Single

internal interface HomeDataSource {
    fun getApps(): Single<Apps>
}
