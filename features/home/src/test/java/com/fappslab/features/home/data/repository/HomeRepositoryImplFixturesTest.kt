package com.fappslab.features.home.data.repository

import com.fappslab.libraries.arch.jsonhandle.readFromJSONToString
import com.fappslab.libraries.arch.testing.rules.RemoteTestRule
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

internal const val GET_APPS_SUCCESS_RESPONSE = "get_apps_success_response.json"
internal const val GET_APPS_FAILURE_RESPONSE = "get_apps_failure_response.json"

internal fun RemoteTestRule.toServerSuccessResponse() {
    val body = readFromJSONToString(GET_APPS_SUCCESS_RESPONSE)
    mockWebServerResponse(body, HttpURLConnection.HTTP_OK)
}

internal fun RemoteTestRule.toServerFailureResponse() {
    val body = readFromJSONToString(GET_APPS_FAILURE_RESPONSE)
    mockWebServerResponse(body, HttpsURLConnection.HTTP_BAD_REQUEST)
}
