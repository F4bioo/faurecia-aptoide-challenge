package com.fappslab.features.details.data.model


import com.google.gson.annotations.SerializedName

internal data class VoteResponse(
    @SerializedName("count") val count: Int?,
    @SerializedName("type") val type: String?
)
