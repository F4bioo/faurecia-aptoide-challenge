package com.fappslab.features.home.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ErrorArgs(
    val message: String?
) : Parcelable
