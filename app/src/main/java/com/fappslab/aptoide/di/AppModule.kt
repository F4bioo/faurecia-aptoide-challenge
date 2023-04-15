package com.fappslab.aptoide.di

import org.koin.core.module.Module
import org.koin.dsl.module

object AppModule {

    val modules
        get() = listOf(presentationModule, dataModule)

    private val presentationModule: Module = module { }

    private val dataModule: Module = module { }
}
