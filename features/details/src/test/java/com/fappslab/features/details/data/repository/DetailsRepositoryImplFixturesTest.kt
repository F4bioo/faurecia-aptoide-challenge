package com.fappslab.features.details.data.repository

import com.fappslab.libraries.arch.jsonhandle.readFromJSONToString
import com.fappslab.libraries.arch.testing.rules.RemoteTestRule
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

internal const val GET_APP_SUCCESS_RESPONSE = "get_app_success_response.json"
internal const val GET_APP_FAILURE_RESPONSE = "get_app_failure_response.json"

internal fun RemoteTestRule.toServerSuccessResponse() {
    val body = readFromJSONToString(GET_APP_SUCCESS_RESPONSE)
    mockWebServerResponse(body, HttpURLConnection.HTTP_OK)
}

internal fun RemoteTestRule.toServerFailureResponse() {
    val body = readFromJSONToString(GET_APP_FAILURE_RESPONSE)
    mockWebServerResponse(body, HttpsURLConnection.HTTP_BAD_REQUEST)
}
