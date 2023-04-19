package com.fappslab.features.home.presentation.extension

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.fappslab.aptoide.features.home.R
import com.fappslab.features.home.presentation.AboutFragment
import com.fappslab.features.home.presentation.ErrorFragment
import com.fappslab.features.home.presentation.HomeFragment
import com.fappslab.libraries.design.dsmodal.build
import com.fappslab.libraries.design.dsmodal.dsModalHost

private const val ERROR_TAG = "showErrorBottomSheet"
private const val ABOUT_TAG = "showAboutBottomSheet"

internal fun RecyclerView.commonSetup() {
    itemAnimator = null
}

internal fun HomeFragment.showErrorBottomSheet(
    shouldShow: Boolean,
    message: String?,
    dismissBlock: () -> Unit
) {
    dsModalHost {
        setFragment = { ErrorFragment.create(message) }
        onDismiss = dismissBlock
    }.build(shouldShow, childFragmentManager, ERROR_TAG)
}

internal fun HomeFragment.showAboutBottomSheet(
    shouldShow: Boolean,
    dismissBlock: () -> Unit
) {
    dsModalHost {
        setFragment = { AboutFragment.create() }
        onDismiss = dismissBlock
    }.build(shouldShow, childFragmentManager, ABOUT_TAG)
}

internal fun HomeFragment.onMenuItem(
    toolbar: Toolbar,
    lifecycleOwner: LifecycleOwner,
    selectedBlock: (Int) -> Unit
) {

    (activity as? AppCompatActivity)?.run {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) =
                menuInflater.inflate(R.menu.home_menu, menu)

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                selectedBlock(menuItem.itemId)
                return false
            }

        }.let { menuProvider ->
            addMenuProvider(menuProvider, lifecycleOwner, Lifecycle.State.RESUMED)
        }
    }
}

internal fun LottieAnimationView.animHandle(shouldAnimLottie: Boolean) {
    if (shouldAnimLottie) {
        resumeAnimation()
    } else pauseAnimation()
}
