package com.fappslab.libraries.arch.di

import android.app.DownloadManager
import android.content.Context
import com.fappslab.aptoide.libraries.arch.BuildConfig
import com.fappslab.libraries.arch.koinload.KoinLoad
import com.fappslab.libraries.arch.network.client.HttpClient
import com.fappslab.libraries.arch.network.client.HttpClientImpl
import com.fappslab.libraries.arch.network.downloader.Downloader
import com.fappslab.libraries.arch.network.downloader.DownloaderImpl
import com.fappslab.libraries.arch.network.interceptor.HeaderInterceptor
import com.fappslab.libraries.arch.network.retrofit.RetrofitClient
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module

object ArchModule : KoinLoad() {

    override val dataModule: Module = module {
        single<HttpClient> {
            HttpClientImpl(
                retrofit = getRetrofitClient().create()
            )
        }
    }

    override val additionalModule: Module = module {
        factory<Downloader> {
            DownloaderImpl(
                downloadManager = get<Context>().getSystemService(
                    DownloadManager::class.java
                )
            )
        }
    }

    private fun getRetrofitClient(): RetrofitClient {
        return RetrofitClient(
            baseUrl = BuildConfig.BASE_URL,
            interceptors = listOf(
                HeaderInterceptor(),
                httpLoggingInterceptor()
            )
        )
    }

    private fun httpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else HttpLoggingInterceptor.Level.NONE
        }
}
