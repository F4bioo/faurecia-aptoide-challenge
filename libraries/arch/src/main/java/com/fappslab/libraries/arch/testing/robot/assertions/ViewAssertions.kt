package com.fappslab.libraries.arch.testing.robot.assertions

import android.widget.ViewFlipper
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.fappslab.libraries.arch.testing.robot.assertions.ViewMatchers.endIconTextInputLayoutClicked
import com.fappslab.libraries.arch.testing.robot.assertions.ViewMatchers.recyclerViewItemCount
import com.fappslab.libraries.arch.testing.robot.assertions.ViewMatchers.withChipViewBackground
import com.fappslab.libraries.arch.testing.robot.assertions.ViewMatchers.withEndIcon
import com.fappslab.libraries.arch.testing.robot.assertions.ViewMatchers.withShapeableImageViewStrokeColor
import com.fappslab.libraries.arch.testing.robot.assertions.ViewMatchers.withViewBackground
import junit.framework.AssertionFailedError
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import kotlin.test.assertEquals

@Suppress("TooManyFunctions")
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object ViewAssertions {

    fun checkIsDisplayed(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun checkIsNotDisplayed(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    fun checkIsEnabled(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.isEnabled()))
    }

    fun checkIsNotEnabled(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()))
    }

    fun checkEndIconTextInputLayoutHasDrawable(@IdRes resId: Int, @DrawableRes iconRes: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(withEndIcon(iconRes)))
    }

    fun checkEndIconTextInputLayoutIsClicked(@IdRes resId1: Int, @IdRes resId2: Int) {
        Espresso.onView(ViewMatchers.withId(resId1))
            .perform(endIconTextInputLayoutClicked(resId2))
    }

    fun checkInputTextHasText(@IdRes resId: Int, expectedString: String) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedString)))
    }

    fun checkInputTextIsEmpty(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }

    fun checkInputTextHasHint(@IdRes resId: Int, expectedString: String) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.withHint(expectedString)))
    }

    fun checkButtonClicked(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .perform(ViewActions.click())
    }

    fun checkTextViewHasExactlyString(@IdRes resId: Int, expectedString: String) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedString)))
    }

    fun checkTextViewHasExactlyStringRes(@IdRes resId: Int, @StringRes expectedStringRes: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.withText(expectedStringRes)))
    }

    fun checkShapeableImageViewStrokeColor(@IdRes resId: Int, @ColorRes expectedColorRes: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(withShapeableImageViewStrokeColor(expectedColorRes)))
    }

    fun checkViewHasExactlyBackgroundColor(@IdRes resId: Int, @ColorRes expectedColorRes: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(withViewBackground(expectedColorRes)))
    }

    fun checkChipHasExactlyBackgroundColor(@IdRes resId: Int, @ColorRes expectedColorRes: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(withChipViewBackground(expectedColorRes)))
    }

    fun recyclerViewIsPopulated(@IdRes resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(recyclerViewItemCount(Matchers.greaterThan(0))))
    }

    fun checkDisplayedFlipperChild(@IdRes viewFlipperId: Int, expectedChild: Int) {
        Espresso.onView(ViewMatchers.withId(viewFlipperId)).check { view, _ ->
            if (view is ViewFlipper) {
                assertEquals(expectedChild, view.displayedChild)
            } else throw AssertionFailedError("The given view id is not a ViewFlipper")
        }
    }
}
