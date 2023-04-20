package com.fappslab.features.home.presentation

import com.fappslab.aptoide.features.home.R
import com.fappslab.features.home.presentation.viewmodel.EMPTY_CHILD
import com.fappslab.libraries.arch.testing.robot.RobotCheck
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkButtonClicked
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkDisplayedFlipperChild
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkIsDisplayed
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkTextViewHasExactlyStringRes
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkViewHasExactlyBackgroundColor
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.recyclerViewIsPopulated
import com.fappslab.aptoide.libraries.design.R as DS

internal const val EMPTY_SCREEN_MESSAGE = "Please check the internet connection and try again."

internal class HomeFragmentRobotCheck : RobotCheck<HomeFragmentRobotCheck> {

    // For loading screen scenario
    fun checkLoadingScreenIsDisplayed() {
        checkIsDisplayed(R.id.loading)
    }

    // For success screen scenario
    fun checkToolbarHasExactlyBackgroundColor() {
        checkViewHasExactlyBackgroundColor(R.id.toolbar_home, DS.color.ds_primary)
    }

    fun checkSuccessScreenIsDisplayed() {
        checkIsDisplayed(R.id.refresh_layout)
    }

    fun checkEditorsTitleHasExactlyText() {
        checkTextViewHasExactlyStringRes(R.id.text_editors_title, R.string.home_editors_title)
    }

    fun checkRecyclerViewEditorsIsPopulated() {
        recyclerViewIsPopulated(R.id.recycler_editors)
    }

    fun checkTopsTitleHasExactlyText() {
        checkTextViewHasExactlyStringRes(R.id.text_tops_title, R.string.home_tops_title)
    }

    fun checkRecyclerViewTopsIsPopulated() {
        recyclerViewIsPopulated(R.id.recycler_tops)
    }

    fun checkTrendingHasExactlyText() {
        checkTextViewHasExactlyStringRes(R.id.text_trending_title, R.string.home_trending_title)
    }

    fun checkRecyclerViewTrendingIsPopulated() {
        recyclerViewIsPopulated(R.id.recycler_trending)
    }

    // For empty screen scenario
    fun checkEmptyScreenIsDisplayed() {
        checkDisplayedFlipperChild(R.id.flipper_container, EMPTY_CHILD)
    }

    fun checkEmptyScreenHasExactlyText() {
        checkIsDisplayed(R.id.include_empty_state)
    }

    fun checkEmptyScreenButtonIsClicked() {
        checkButtonClicked(R.id.button_empty)
    }
}
