package com.fappslab.aptoide

import android.app.Application
import com.fappslab.aptoide.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration

class Application : Application() {

    private val modules by lazy {
        AppModule.modules
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = appDeclaration())
        modules.load()
    }

    override fun onTerminate() {
        modules.unload()
        stopKoin()
        super.onTerminate()
    }

    private fun appDeclaration(): KoinAppDeclaration = {
        androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        androidContext(androidContext = this@Application)
    }

    private fun List<Module>.load() {
        loadKoinModules(modules = this)
    }

    private fun List<Module>.unload() {
        unloadKoinModules(modules = this)
    }
}
