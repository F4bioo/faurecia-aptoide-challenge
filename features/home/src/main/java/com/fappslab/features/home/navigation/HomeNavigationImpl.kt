package com.fappslab.features.home.navigation

import androidx.fragment.app.Fragment
import com.fappslab.features.home.presentation.HomeFragment
import com.fappslab.libraries.navigation.HomeNavigation

internal class HomeNavigationImpl : HomeNavigation {

    override fun create(): Fragment =
        HomeFragment.create()
}
