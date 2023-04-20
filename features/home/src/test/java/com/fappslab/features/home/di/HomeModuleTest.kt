package com.fappslab.features.home.di

import com.fappslab.libraries.arch.testing.networkmodule.provideNetworkModule
import com.fappslab.libraries.arch.testing.rules.SchedulerTestRule
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

internal class HomeModuleTest : AutoCloseKoinTest() {

    @get:Rule
    val schedulersRule = SchedulerTestRule()

    private val mockedModules = provideNetworkModule()

    @Test
    fun `checkModules Should Koin provides dependencies When invoke HomeModule`() {
        startKoin {
            modules(HomeModule.modules + mockedModules)
        }.checkModules()
    }
}
