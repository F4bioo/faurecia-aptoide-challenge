package com.fappslab.libraries.arch.testing.robot.assertions

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.VisibleForTesting
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.google.android.material.chip.Chip
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher

@Suppress("TooManyFunctions")
@VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
object ViewMatchers {

    internal fun withEndIcon(
        @DrawableRes iconRes: Int
    ): Matcher<View> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with end icon drawable")
            }

            override fun matchesSafely(textInputLayout: TextInputLayout): Boolean {
                val drawable = ContextCompat.getDrawable(textInputLayout.context, iconRes)

                return drawable == textInputLayout.endIconDrawable
            }
        }
    }

    internal fun endIconTextInputLayoutDrawable(
        @DrawableRes iconRes: Int
    ): BoundedMatcher<View, TextInputLayout> {
        return object : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("is end icon drawable")
            }

            override fun matchesSafely(textInputLayout: TextInputLayout): Boolean {
                val drawableExpected =
                    ContextCompat.getDrawable(textInputLayout.context, iconRes)?.toBitmap()
                return textInputLayout.endIconDrawable?.toBitmap()
                    ?.sameAs(drawableExpected) == true
            }
        }
    }

    internal fun endIconTextInputLayoutClicked(
        @IdRes idRes: Int
    ): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(
                    isDisplayed(),
                    isAssignableFrom(TextInputLayout::class.java)
                )
            }

            override fun getDescription(): String {
                return "click end icon"
            }

            override fun perform(uiController: UiController, view: View) {
                val textInputLayout = view as TextInputLayout
                val endIcon = textInputLayout.findViewById<ImageView>(idRes)
                endIcon.performClick()
            }
        }
    }

    internal fun withChipViewBackground(
        @ColorRes expectedColorRes: Int
    ): BoundedMatcher<View, Chip> {
        return object : BoundedMatcher<View, Chip>(Chip::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with background color: $expectedColorRes")
            }

            override fun matchesSafely(item: Chip): Boolean {
                return item.chipBackgroundColor?.defaultColor ==
                        ContextCompat.getColor(item.context, expectedColorRes)
            }
        }
    }

    internal fun withShapeableImageViewStrokeColor(
        @ColorRes expectedColorRes: Int
    ): BoundedMatcher<View, ShapeableImageView> {
        return object : BoundedMatcher<View, ShapeableImageView>(ShapeableImageView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with stroke color: $expectedColorRes")
            }

            override fun matchesSafely(item: ShapeableImageView): Boolean {
                return item.strokeColor?.defaultColor ==
                        ContextCompat.getColor(item.context, expectedColorRes)
            }
        }
    }

    internal fun withViewBackground(
        @ColorRes expectedColorRes: Int
    ): BoundedMatcher<View, View> {
        return object : BoundedMatcher<View, View>(View::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("with background color: $expectedColorRes")
            }

            override fun matchesSafely(item: View): Boolean {
                return (item.background as? ColorDrawable)?.color ==
                        ContextCompat.getColor(item.context, expectedColorRes)
            }
        }
    }

    internal fun recyclerViewItemCount(
        matcher: Matcher<Int>
    ): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("RecyclerView with item count: ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                return matcher.matches(view.adapter?.itemCount)
            }
        }
    }
}
