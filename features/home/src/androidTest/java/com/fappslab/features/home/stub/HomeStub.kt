package com.fappslab.features.home.stub

import com.fappslab.features.home.data.mapper.toApps
import com.fappslab.features.home.data.model.AppsResponse
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel

internal const val GET_APPS_SUCCESS_RESPONSE = "get_apps_success_response.json"
internal const val GET_APPS_FAILURE_RESPONSE = "get_apps_failure_response.json"

internal fun appsStub() = readFromJSONToModel<AppsResponse>(GET_APPS_SUCCESS_RESPONSE).toApps()

internal fun appStub() = appsStub().list.first()
