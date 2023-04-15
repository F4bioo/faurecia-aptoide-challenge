package com.fappslab.features.home.data.api

import com.fappslab.features.home.data.model.AppsResponse
import io.reactivex.Single
import retrofit2.http.GET

internal interface HomeService {
    @GET("api/6/bulkRequest/api_list/listApps")
    fun getApps(): Single<AppsResponse>
}
