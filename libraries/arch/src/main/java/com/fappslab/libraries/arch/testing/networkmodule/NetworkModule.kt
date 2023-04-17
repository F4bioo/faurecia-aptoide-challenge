package com.fappslab.libraries.arch.testing.networkmodule

import androidx.annotation.VisibleForTesting
import com.fappslab.libraries.arch.network.client.HttpClient
import com.fappslab.libraries.arch.network.client.HttpClientImpl
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
fun provideNetworkModule(httpUrl: HttpUrl = "http://url".toHttpUrl()): Module = module {
    factory<Retrofit> {
        Retrofit.Builder()
            .baseUrl(httpUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    factory<HttpClient> { HttpClientImpl(retrofit = get()) }
}
