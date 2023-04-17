package com.fappslab.features.details.stub

import com.fappslab.features.details.data.mapper.toApp
import com.fappslab.features.details.data.model.AppResponse
import com.fappslab.features.details.data.repository.GET_APP_SUCCESS_RESPONSE
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel

internal fun appStub() = readFromJSONToModel<AppResponse>(GET_APP_SUCCESS_RESPONSE).toApp()
