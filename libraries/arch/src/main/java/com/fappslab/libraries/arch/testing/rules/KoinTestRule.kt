package com.fappslab.libraries.arch.testing.rules

import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest

abstract class KoinTestRule : TestWatcher(), KoinTest {

    open fun setupModules(): Module = module { }

    override fun starting(description: Description) {
        super.starting(description)
        stopKoin()
        startKoin { loadKoinModules(setupModules()) }
    }

    override fun finished(description: Description) {
        super.finished(description)
        stopKoin()
    }
}
