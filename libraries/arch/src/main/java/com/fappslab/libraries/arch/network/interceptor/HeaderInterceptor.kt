package com.fappslab.libraries.arch.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class HeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Connection", "close")
            .removeHeader("Content-Length")
            .build()

        return chain.proceed(request)
    }
}
