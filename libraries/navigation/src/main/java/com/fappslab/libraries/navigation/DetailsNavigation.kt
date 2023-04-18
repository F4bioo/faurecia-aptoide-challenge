package com.fappslab.libraries.navigation

import android.content.Context
import android.content.Intent

interface DetailsNavigation {
    fun create(context: Context, packageName: String): Intent
}
