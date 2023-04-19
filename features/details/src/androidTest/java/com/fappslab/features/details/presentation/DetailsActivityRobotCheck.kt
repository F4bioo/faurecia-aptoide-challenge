package com.fappslab.features.details.presentation

import com.fappslab.aptoide.features.details.R
import com.fappslab.libraries.arch.testing.robot.RobotCheck
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkButtonClicked
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkImageViewHasDrawable
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkIsDisplayed
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkShapeableImageViewHasDrawable
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkTextViewHasExactlyString
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkTextViewHasExactlyStringRes
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.checkTextViewHasExactlyStringWithNewLine
import com.fappslab.libraries.arch.testing.robot.assertions.ViewAssertions.recyclerViewIsPopulated
import com.fappslab.aptoide.libraries.design.R as DS

internal class DetailsActivityRobotCheck : RobotCheck<DetailsActivityRobotCheck> {

    // For loading screen scenario
    fun checkLoadingScreenIsDisplayed() {
        checkIsDisplayed(R.id.include_loading_state)
    }

    // For empty screen scenario
    fun checkEmptyScreenIsDisplayed() {
        checkIsDisplayed(R.id.include_empty_state)
    }

    fun checkEmptyScreenHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyStringWithNewLine(
            R.id.text_message,
            expectedString = expectedString
        )
    }

    fun checkEmptyScreenPrimaryButtonIsClicked() {
        checkButtonClicked(R.id.button_primary)
    }

    fun checkEmptyScreenSecondaryButtonIsClicked() {
        checkButtonClicked(R.id.button_secondary)
    }

    // For success header scenario
    fun checkBackArrowHasExactlyDrawable() {
        checkImageViewHasDrawable(
            R.id.button_back,
            DS.drawable.ds_ic_arrow_back,
            DS.color.ds_gray_light
        )
    }

    fun checkImageAvatarHasDrawable() {
        checkShapeableImageViewHasDrawable(R.id.image_details_avatar)
    }

    fun checkImageRankHasExactlyDrawable() {
        checkImageViewHasDrawable(
            R.id.image_rank,
            DS.drawable.ds_ic_shield_2,
            DS.color.ds_green
        )
    }

    fun checkTextRankHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyString(
            R.id.text_rank,
            expectedString = expectedString
        )
    }

    fun checkTextTitleNameHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyString(
            R.id.text_tile,
            expectedString = expectedString
        )
    }

    fun checkTextDownloadsHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyString(
            R.id.text_downloads,
            expectedString = expectedString
        )
    }

    fun checkTextSizeHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyString(
            R.id.text_size,
            expectedString = expectedString
        )
    }

    fun checkRecyclerViewScreenshotsIsPopulated() {
        recyclerViewIsPopulated(R.id.recycler_screenshots)
    }

    // For success description scenario
    fun checkTextTitleDescriptionHasExactlyStringRes() {
        checkTextViewHasExactlyStringRes(
            R.id.text_description_title,
            R.string.details_bout_description
        )
    }

    fun checkTextDescriptionHasExactlyStringRes(expectedString: String) {
        checkTextViewHasExactlyString(
            R.id.text_description,
            expectedString = expectedString
        )
    }

    fun checkTextTitleAboutHasExactlyStringRes() {
        checkTextViewHasExactlyStringRes(
            R.id.text_more_title,
            R.string.details_bout_store
        )
    }

    fun checkTextEmailHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyStringWithNewLine(
            R.id.text_email,
            expectedString = expectedString
        )
    }


    fun checkTextNameHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyStringWithNewLine(
            R.id.text_name,
            expectedString = expectedString
        )
    }

    fun checkTextPrivacyHasExactlyString(expectedString: String) {
        checkTextViewHasExactlyStringWithNewLine(
            R.id.text_privacy,
            expectedString = expectedString
        )
    }
}
