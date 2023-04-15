package com.fappslab.features.home.domain.repository

import com.fappslab.features.home.domain.model.Apps
import io.reactivex.Single

internal interface HomeRepository {
    fun getApps(): Single<Apps>
}
