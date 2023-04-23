package com.fappslab.features.details.di

import com.fappslab.features.details.data.api.DetailsService
import com.fappslab.features.details.data.repository.DetailsRepositoryImpl
import com.fappslab.features.details.data.source.DetailsDataSourceImpl
import com.fappslab.features.details.domain.usecase.GetAppUseCase
import com.fappslab.features.details.navigation.DetailsNavigationImpl
import com.fappslab.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.libraries.arch.koinload.KoinLoad
import com.fappslab.libraries.arch.network.client.HttpClient
import com.fappslab.libraries.navigation.DetailsNavigation
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

object DetailsModule : KoinLoad() {

    override val presentationModule: Module = module {
        viewModel { (packageName: String) ->
            val repository = getDetailsRepository()
            DetailsViewModel(
                packageName = packageName,
                getAppUseCase = GetAppUseCase(
                    repository = repository
                )
            )
        }
    }

    override val additionalModule: Module = module {
        factory<DetailsNavigation> { DetailsNavigationImpl() }
    }

    private fun Scope.getDetailsRepository() =
        DetailsRepositoryImpl(
            dataSource = DetailsDataSourceImpl(
                service = get<HttpClient>().create(
                    clazz = DetailsService::class.java
                )
            )
        )
}
