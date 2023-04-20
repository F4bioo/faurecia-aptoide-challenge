package com.fappslab.libraries.arch.testing.robot.assertions.custommatchers

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

private const val NO_DRAWABLE = 0
private const val HAS_DRAWABLE = -1

fun withDrawable(
    @DrawableRes drawableRes: Int,
    @ColorRes colorRes: Int? = null
): Matcher<View> {
    return ImageViewDrawableMatcher(drawableRes, colorRes)
}

fun withBackgroundDrawable(
    @DrawableRes expectedDrawableId: Int,
    @ColorRes expectedDrawableColorId: Int? = null
): Matcher<View> = BackgroundDrawableMatcher(expectedDrawableId, expectedDrawableColorId)

fun withTextViewStartDrawable(
    @DrawableRes drawableResId: Int,
    @ColorRes expectedDrawableColorRes: Int? = null
): Matcher<View> {
    return TextViewStartDrawableMatcher(drawableResId, expectedDrawableColorRes)
}

internal class ImageViewDrawableMatcher(
    @DrawableRes expectedDrawableId: Int,
    @ColorRes expectedDrawableColorId: Int? = null
) : DrawableMatcher<ImageView>(ImageView::class.java, expectedDrawableId, expectedDrawableColorId) {

    override fun getDrawable(view: ImageView): Drawable {
        return view.drawable
    }
}

internal class BackgroundDrawableMatcher(
    @DrawableRes expectedDrawableId: Int,
    @ColorRes expectedDrawableColorId: Int? = null
) : DrawableMatcher<View>(View::class.java, expectedDrawableId, expectedDrawableColorId) {

    override fun getDrawable(view: View): Drawable {
        return view.background
    }
}

internal class TextViewStartDrawableMatcher(
    @DrawableRes expectedDrawableId: Int,
    @ColorRes expectedDrawableColorId: Int? = null
) : DrawableMatcher<TextView>(TextView::class.java, expectedDrawableId, expectedDrawableColorId) {

    override fun getDrawable(view: TextView): Drawable? {
        return view.compoundDrawables.firstOrNull()
    }
}

abstract class DrawableMatcher<V : View>(
    clazz: Class<V>,
    @DrawableRes private val expectedDrawableId: Int,
    @ColorRes private val expectedDrawableColorId: Int? = null
) : BoundedMatcher<View, V>(clazz) {

    abstract fun getDrawable(view: V): Drawable?

    private var resources: Resources? = null

    override fun matchesSafely(view: V): Boolean {
        resources = view.context.resources

        return matchesDrawableSafely(
            view.context,
            getDrawable(view),
            expectedDrawableId,
            expectedDrawableColorId
        )
    }

    override fun describeTo(description: Description) {
        describeDrawableTo(description, expectedDrawableId, resources)
    }
}

internal fun matchesDrawableSafely(
    context: Context,
    drawable: Drawable?,
    expectedDrawableId: Int,
    @ColorRes expectedDrawableColorId: Int? = null
): Boolean = when (expectedDrawableId) {
    NO_DRAWABLE -> drawable == null
    HAS_DRAWABLE -> drawable != null
    else -> AppCompatResources.getDrawable(context, expectedDrawableId)?.let { expectedDrawable ->
        val expectedBitmap: Bitmap = expectedDrawable
            .tintDrawableIfNecessary(context, expectedDrawableColorId)
            .toBitmap()

        expectedBitmap.sameAs(drawable?.toBitmap())
    } ?: false
}

private fun Drawable.tintDrawableIfNecessary(
    context: Context,
    @ColorRes expectedColorId: Int?
): Drawable {
    if (expectedColorId == null) return this
    return this.apply {
        val wrappedDrawable = DrawableCompat.wrap(this)
        DrawableCompat.setTint(
            wrappedDrawable,
            ContextCompat.getColor(context, expectedColorId)
        )
    }
}

private fun describeDrawableTo(
    description: Description,
    expectedDrawableId: Int,
    resources: Resources?
) {
    when (expectedDrawableId) {
        NO_DRAWABLE -> description.appendText("has drawable")
        HAS_DRAWABLE -> description.appendText("has no drawable")
        else -> {
            if (resources == null) {
                description.appendText("has drawable: ")
                description.appendValue(expectedDrawableId)
            } else {
                val resourceName = getResourceNameSafely(resources, expectedDrawableId)
                description.appendText("has drawable: ")
                description.appendText(resourceName)
            }
        }
    }
}

private fun getResourceNameSafely(resources: Resources, resourceId: Int): String {
    return try {
        resources.getResourceName(resourceId)
    } catch (e: Resources.NotFoundException) {
        "$resourceId -> resource name not found, cause: ${e.message}"
    }
}
