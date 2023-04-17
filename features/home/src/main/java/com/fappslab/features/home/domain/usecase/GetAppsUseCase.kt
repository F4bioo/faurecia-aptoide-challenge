package com.fappslab.features.home.domain.usecase

import com.fappslab.features.home.domain.model.Apps
import com.fappslab.features.home.domain.repository.HomeRepository
import io.reactivex.Single

internal class GetAppsUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Single<Apps> =
        repository.getApps()
}
