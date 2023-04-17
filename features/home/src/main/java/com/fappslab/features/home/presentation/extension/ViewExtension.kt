package com.fappslab.features.home.presentation.extension

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.fappslab.aptoide.features.home.R
import com.fappslab.features.home.presentation.AboutFragment
import com.fappslab.features.home.presentation.ErrorFragment
import com.fappslab.features.home.presentation.HomeFragment
import com.fappslab.libraries.arch.extension.ImageLoadParams
import com.fappslab.libraries.design.dsmodal.build
import com.fappslab.libraries.design.dsmodal.dsModalHost
import com.google.android.material.appbar.MaterialToolbar
import com.fappslab.aptoide.libraries.design.R as DS

private const val ERROR_TAG = "showErrorBottomSheet"
private const val ABOUT_TAG = "showAboutBottomSheet"

internal fun RecyclerView.commonSetup() {
    setHasFixedSize(true)
    itemAnimator = null
}

internal fun String.params() = ImageLoadParams(
    imageUrl = this,
    placeholderRes = DS.drawable.ds_illu_waiting,
    errorRes = DS.drawable.ds_illu_error_gallery
)

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
    toolbar: MaterialToolbar,
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
