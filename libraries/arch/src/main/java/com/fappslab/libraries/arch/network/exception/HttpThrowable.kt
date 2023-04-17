package com.fappslab.libraries.arch.network.exception

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HttpThrowable(
    @SerializedName("message") override val message: String? = null,
    @Expose private val throwable: Throwable? = null
) : Throwable(message, throwable)
