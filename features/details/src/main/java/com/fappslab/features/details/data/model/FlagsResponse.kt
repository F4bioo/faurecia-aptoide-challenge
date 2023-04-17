package com.fappslab.features.details.data.model


import com.google.gson.annotations.SerializedName

internal data class FlagsResponse(
    @SerializedName("votes") val votes: List<VoteResponse>?
)
