package com.fappslab.libraries.arch.network.interceptor

import com.fappslab.aptoide.libraries.arch.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

private const val TAG = "OkHttp"

class LoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val httpLoggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor { Timber.tag(TAG).d(it) }
                .apply { level = HttpLoggingInterceptor.Level.BODY }
        } else HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.NONE }

        return httpLoggingInterceptor.intercept(chain)
    }
}
