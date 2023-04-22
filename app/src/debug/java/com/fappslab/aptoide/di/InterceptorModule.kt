package com.fappslab.aptoide.di

import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.core.FlipperPlugin
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.fappslab.aptoide.debugtools.SharedPreferencesPlugin.getDescriptors
import com.fappslab.libraries.arch.di.RetrofitInterceptorQualifier
import com.fappslab.libraries.arch.koinload.KoinLoad
import com.fappslab.libraries.arch.koinload.KoinQualifier
import com.fappslab.libraries.arch.network.interceptor.HeaderInterceptor
import com.fappslab.libraries.arch.network.interceptor.LoggingInterceptor
import okhttp3.Interceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal object FlipperPluginQualifier : KoinQualifier

object InterceptorModule : KoinLoad() {

    override val dataModule = module {
        single(qualifier = RetrofitInterceptorQualifier) {
            listOf<Interceptor>(
                HeaderInterceptor(),
                LoggingInterceptor(),
                get<FlipperOkhttpInterceptor>()
            )
        }
    }

    override val additionalModule = module {
        single { NetworkFlipperPlugin() }
        single { AndroidFlipperClient.getInstance(androidContext()) }
        single { FlipperOkhttpInterceptor(get<NetworkFlipperPlugin>(), true) }
        single(qualifier = FlipperPluginQualifier) {
            listOf<FlipperPlugin>(
                SharedPreferencesFlipperPlugin(androidContext(), getDescriptors(androidContext())),
                InspectorFlipperPlugin(androidContext(), DescriptorMapping.withDefaults()),
                DatabasesFlipperPlugin(androidContext()),
                NavigationFlipperPlugin.getInstance(),
                CrashReporterPlugin.getInstance(),
                LeakCanary2FlipperPlugin(),
                get<NetworkFlipperPlugin>()
            )
        }
    }
}
