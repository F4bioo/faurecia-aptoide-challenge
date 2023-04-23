package com.fappslab.libraries.arch.di

import android.app.DownloadManager
import android.content.Context
import io.mockk.every
import io.mockk.mockk
import okhttp3.Interceptor
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

internal class ArchModuleTest : AutoCloseKoinTest() {

    private val mockedModules = module {
        factory(qualifier = RetrofitInterceptorQualifier) {
            listOf<Interceptor>(element = mockk())
        }
    }

    @Test
    fun `checkModules Should Koin provides dependencies When invoke ArchModule`() {
        val context = mockk<Context>()
        every { context.getSystemService(DownloadManager::class.java) } returns mockk()

        startKoin {
            androidContext(context)
            modules(ArchModule.modules + mockedModules)
        }.checkModules()
    }
}
