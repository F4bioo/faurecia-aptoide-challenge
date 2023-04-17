package com.fappslab.libraries.arch.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

private const val RETURN_TYPE = "json"

internal class DefInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestUrl = request.url
        val newUrl = requestUrl.newBuilder()
            .addPathSegment(RETURN_TYPE)
            .build()

        return chain.proceed(
            request.newBuilder()
                .url(newUrl)
                .build()
        )
    }
}
