package com.fappslab.libraries.arch.di

import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

internal class ArchModuleTest : AutoCloseKoinTest() {

    @Test
    fun `checkModules Should Koin provides dependencies When invoke ArchModule`() {
        startKoin {
            modules(ArchModule.modules)
        }.checkModules()
    }
}
