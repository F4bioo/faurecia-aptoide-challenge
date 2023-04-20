package com.fappslab.features.details.data.api

import com.fappslab.features.details.data.model.AppResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DetailsService {
    @GET("api/7/app/getMeta/package_name={packageName}")
    fun getApp(
        @Path("packageName") packageName: String
    ): Single<AppResponse>
}
