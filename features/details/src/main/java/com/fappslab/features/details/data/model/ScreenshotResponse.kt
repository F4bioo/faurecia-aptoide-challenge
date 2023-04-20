package com.fappslab.features.details.data.model


import com.google.gson.annotations.SerializedName

internal data class ScreenshotResponse(
    @SerializedName("url") val imageUrl: String?
)
