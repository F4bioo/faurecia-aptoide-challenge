package com.fappslab.features.details.stub

import com.fappslab.features.details.data.mapper.toApp
import com.fappslab.features.details.data.model.AppResponse
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel

internal const val GET_APP_SUCCESS_RESPONSE = "get_app_success_response.json"

internal fun appStub() = readFromJSONToModel<AppResponse>(GET_APP_SUCCESS_RESPONSE).toApp()
