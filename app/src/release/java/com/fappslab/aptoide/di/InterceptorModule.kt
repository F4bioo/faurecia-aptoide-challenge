package com.fappslab.aptoide.di

import com.fappslab.libraries.arch.di.RetrofitInterceptorQualifier
import com.fappslab.libraries.arch.koinload.KoinLoad
import com.fappslab.libraries.arch.network.interceptor.HeaderInterceptor
import okhttp3.Interceptor
import org.koin.dsl.module

object InterceptorModule : KoinLoad() {

    override val dataModule = module {
        single(qualifier = RetrofitInterceptorQualifier) {
            listOf<Interceptor>(
                HeaderInterceptor()
            )
        }
    }
}
