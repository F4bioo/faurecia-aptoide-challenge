package com.fappslab.aptoide

import android.app.Application
import com.fappslab.aptoide.di.InterceptorModule
import com.fappslab.features.details.di.DetailsModule
import com.fappslab.features.home.di.HomeModule
import com.fappslab.libraries.arch.di.ArchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.dsl.KoinAppDeclaration
import timber.log.Timber

open class ReleaseApplication : Application() {

    private val appDeclaration
        get(): KoinAppDeclaration = {
            Timber.plant(Timber.DebugTree())
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@ReleaseApplication)
        }

    override fun onCreate() {
        super.onCreate()
        startKoin(appDeclaration = appDeclaration)
        koinLoad()
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }

    private fun koinLoad() {
        InterceptorModule.load()
        ArchModule.load()
        HomeModule.load()
        DetailsModule.load()
    }
}
