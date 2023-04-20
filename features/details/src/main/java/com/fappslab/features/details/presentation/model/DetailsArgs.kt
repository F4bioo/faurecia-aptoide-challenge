package com.fappslab.features.details.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class DetailsArgs(
    val packageName: String
) : Parcelable
