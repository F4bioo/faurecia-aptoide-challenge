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
import com.fappslab.features.home.presentation.HomeFragment
import com.fappslab.libraries.design.dsmodal.build
import com.fappslab.libraries.design.dsmodal.dsModalHost
import com.fappslab.aptoide.libraries.design.R as DS

private const val ERROR_TAG = "showErrorBottomSheet"
private const val ABOUT_TAG = "showAboutBottomSheet"

internal fun RecyclerView.commonSetup() {
    itemAnimator = null
}

internal fun HomeFragment.showErrorBottomSheet(message: String?, actionPrimary: () -> Unit) {
    dsModalHost {
        imageRes = DS.drawable.ds_illu_moon
        titleRes = DS.string.common_error_title
        messageText = message ?: getString(DS.string.common_error_message)
        primaryButton = {
            buttonTextRes = DS.string.common_try_again
            buttonAction = {
                dismiss()
                actionPrimary()
            }
        }
    }.build(manager = childFragmentManager, tag = ERROR_TAG)
}

internal fun HomeFragment.showAboutBottomSheet() {
    dsModalHost {
        imageRes = DS.drawable.ds_illu_challenge
        titleRes = R.string.home_about_title
        messageRes = R.string.home_about_message
        primaryButton = {
            buttonTextRes = DS.string.common_got_it
            buttonAction = { dismiss() }
        }
    }.build(manager = childFragmentManager, tag = ABOUT_TAG)
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

internal fun LottieAnimationView.animHandle(shouldAnim: Boolean) {
    if (shouldAnim) {
        resumeAnimation()
    } else pauseAnimation()
}
