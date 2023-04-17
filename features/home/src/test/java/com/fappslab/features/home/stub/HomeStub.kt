package com.fappslab.features.home.stub

import com.fappslab.features.home.data.mapper.toApps
import com.fappslab.features.home.data.model.AppsResponse
import com.fappslab.features.home.data.repository.GET_APPS_SUCCESS_RESPONSE
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel

internal fun appsStub() = readFromJSONToModel<AppsResponse>(GET_APPS_SUCCESS_RESPONSE).toApps()

internal fun appStub() = appsStub().list.first()
