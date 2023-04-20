package com.fappslab.features.details.domain.usecase

import com.fappslab.features.details.domain.model.App
import com.fappslab.features.details.domain.repository.DetailsRepository
import io.reactivex.Single

internal class GetAppUseCase(
    private val repository: DetailsRepository
) {

    operator fun invoke(packageName: String): Single<App> =
        repository.getApp(packageName)
}
