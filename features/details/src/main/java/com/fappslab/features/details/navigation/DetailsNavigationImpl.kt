package com.fappslab.features.details.navigation

import android.content.Context
import com.fappslab.features.details.presentation.DetailsActivity
import com.fappslab.features.details.presentation.model.DetailsArgs
import com.fappslab.libraries.navigation.DetailsNavigation

internal class DetailsNavigationImpl : DetailsNavigation {

    override fun create(context: Context, packageName: String) =
        DetailsActivity.create(context, args = DetailsArgs(packageName))
}
