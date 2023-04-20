package com.fappslab.libraries.arch.network.exception

import com.google.gson.annotations.SerializedName

internal data class RemoteThrowable(
    @SerializedName("errors") val errors: List<Error>?
) {
    internal data class Error(
        @SerializedName("description") val description: String?
    )
}
