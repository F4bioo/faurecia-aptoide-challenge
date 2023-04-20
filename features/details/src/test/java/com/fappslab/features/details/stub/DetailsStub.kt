package com.fappslab.features.details.stub

import com.fappslab.features.details.data.mapper.toApp
import com.fappslab.features.details.data.model.AppResponse
import com.fappslab.features.details.data.repository.GET_APP_SUCCESS_RESPONSE
import com.fappslab.features.details.domain.model.App
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel
import io.reactivex.Single

internal fun appStub() = readFromJSONToModel<AppResponse>(GET_APP_SUCCESS_RESPONSE).toApp()

internal enum class ReturnsType(val type: Single<App>) {
    SUCCESS(type = Single.just(appStub())),
    FAILURE(type = Single.error(Throwable("Some error")))
}
