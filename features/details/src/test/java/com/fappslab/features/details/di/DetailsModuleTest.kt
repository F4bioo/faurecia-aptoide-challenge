package com.fappslab.features.details.di

import com.fappslab.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.libraries.arch.testing.networkmodule.provideNetworkModule
import com.fappslab.libraries.arch.testing.rules.SchedulerTestRule
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

internal class DetailsModuleTest : AutoCloseKoinTest() {

    @get:Rule
    val schedulersRule = SchedulerTestRule()

    private val mockedModules = provideNetworkModule()

    @Test
    fun `checkModules Should Koin provides dependencies When invoke DetailsModule`() {
        startKoin {
            modules(DetailsModule.modules + mockedModules)
        }.checkModules {
            create<DetailsViewModel> { parametersOf("com.instagram.android") }
        }
    }
}
