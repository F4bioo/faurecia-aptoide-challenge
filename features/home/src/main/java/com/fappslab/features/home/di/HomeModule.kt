package com.fappslab.features.home.di

import com.fappslab.features.home.data.api.HomeService
import com.fappslab.features.home.data.repository.HomeRepositoryImpl
import com.fappslab.features.home.data.source.HomeDataSourceImpl
import com.fappslab.features.home.domain.usecase.GetAppsUseCase
import com.fappslab.features.home.presentation.viewmodel.HomeViewModel
import com.fappslab.libraries.arch.koinload.KoinLoad
import com.fappslab.libraries.arch.network.client.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

object HomeModule : KoinLoad() {

    override val presentation: Module = module {
        viewModel {
            val repository = getHomeRepository()
            HomeViewModel(
                getAppsUseCase = GetAppsUseCase(
                    repository = repository
                )
            )
        }
    }

    private fun Scope.getHomeRepository() =
        HomeRepositoryImpl(
            dataSource = HomeDataSourceImpl(
                service = get<HttpClient>().create(
                    clazz = HomeService::class.java
                )
            )
        )
}
