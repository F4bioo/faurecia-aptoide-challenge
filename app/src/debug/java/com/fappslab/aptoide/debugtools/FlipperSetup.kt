package com.fappslab.aptoide.debugtools

import android.content.Context
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.core.FlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakEventListener
import com.facebook.soloader.SoLoader
import com.fappslab.aptoide.di.FlipperPluginQualifier
import leakcanary.LeakCanary
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject

@OptIn(KoinApiExtension::class)
object FlipperSetup : KoinComponent {

    private val context: Context by inject()

    init {
        setupLeakCanaryPlugin()
        SoLoader.init(context, false)
    }

    fun start() {
        if (FlipperUtils.shouldEnableFlipper(context)) {
            val client: FlipperClient = get()
            val plugins: List<FlipperPlugin> = get(qualifier = FlipperPluginQualifier)
            plugins.forEach { client.addPlugin(it) }
            client.start()
        }
    }

    private fun setupLeakCanaryPlugin() {
        LeakCanary.config = LeakCanary.config.run {
            copy(eventListeners = eventListeners + FlipperLeakEventListener())
        }
    }
}
