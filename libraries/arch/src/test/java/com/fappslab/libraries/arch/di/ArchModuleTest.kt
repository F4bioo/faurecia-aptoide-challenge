package com.fappslab.libraries.arch.di

import android.app.DownloadManager
import android.content.Context
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

internal class ArchModuleTest : AutoCloseKoinTest() {

    private val context: Context = mockk()
    private val downloadManager: DownloadManager = mockk()

    @Test
    fun `checkModules Should Koin provides dependencies When invoke ArchModule`() {
        every { context.getSystemService(DownloadManager::class.java) } returns downloadManager

        startKoin {
            androidContext(context)
            modules(ArchModule.modules)
        }.checkModules()
    }
}
