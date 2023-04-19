package com.fappslab.features.details.presentation

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fappslab.features.details.navigation.DetailsNavigationImpl
import com.fappslab.features.details.presentation.viewmodel.DetailsViewAction
import com.fappslab.features.details.presentation.viewmodel.DetailsViewState
import com.fappslab.features.details.presentation.viewmodel.EMPTY_CHILD
import com.fappslab.features.details.presentation.viewmodel.LOADING_CHILD
import com.fappslab.features.details.presentation.viewmodel.SUCCESS_CHILD
import com.fappslab.features.details.stub.appStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
internal class DetailsActivityTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = "com.instagram.android"
    private val navigation = DetailsNavigationImpl()

    @get:Rule
    val robot = DetailsActivityRobot(intent = navigation.create(context, packageName))


    private val app = appStub()
    private val initialState = DetailsViewState()

    @Test
    fun loading_screen_Should_all_checks_pass_When_display_loading_screen() {
        val loadingState = initialState.copy(
            flipperChild = LOADING_CHILD
        )

        robot
            .givenState { loadingState }
            .whenLaunch()
            .thenCheck { checkLoadingScreenIsDisplayed() }
    }

    @Test
    fun empty_screen_Should_all_checks_pass_When_display_empty_screen() {
        val expectedEmptyMessage = "Application 'package:com.instagram.android' not found"
        val loadingState = initialState.copy(
            errorMessage = expectedEmptyMessage,
            flipperChild = EMPTY_CHILD
        )

        robot
            .givenState { loadingState }
            .givenAction(
                invoke = { onBack() },
                action = { DetailsViewAction.FinishView }
            )
            .whenLaunch()
            .thenCheck { checkEmptyScreenIsDisplayed() }
            .thenCheck { checkEmptyScreenHasExactlyString(expectedEmptyMessage) }
            .thenCheck { checkEmptyScreenPrimaryButtonIsClicked() }
            .thenCheck { checkEmptyScreenSecondaryButtonIsClicked() }
    }

    @Test
    fun success_screen_Should_all_checks_pass_When_display_success_header_screen() {
        val successState = initialState.copy(
            screenshots = app.content.screenshots,
            flipperChild = SUCCESS_CHILD,
            app = app
        )

        robot
            .givenState { successState }
            .whenLaunch()
            .thenCheck { checkBackArrowHasExactlyDrawable() }
            .thenCheck { checkImageAvatarHasDrawable() }
            .thenCheck { checkImageRankHasExactlyDrawable() }
            .thenCheck { checkTextRankHasExactlyString(app.apk.malware.rank) }
            .thenCheck { checkTextTitleNameHasExactlyString(app.name) }
            .thenCheck { checkTextDownloadsHasExactlyString(app.downloads) }
            .thenCheck { checkTextSizeHasExactlyString(app.apk.fileSize) }
            .thenCheck { checkRecyclerViewScreenshotsIsPopulated() }
    }

    @Test
    fun success_screen_Should_all_checks_pass_When_display_success_description_screen() {
        val successState = initialState.copy(
            screenshots = app.content.screenshots,
            flipperChild = SUCCESS_CHILD,
            app = app
        )

        robot
            .givenState { successState }
            .whenLaunch()
            .thenCheck { checkTextTitleDescriptionHasExactlyStringRes() }
            .thenCheck { checkTextDescriptionHasExactlyStringRes(app.content.description) }
            .thenCheck { checkTextTitleAboutHasExactlyStringRes() }
            .thenCheck { checkTextEmailHasExactlyString(app.developer.email) }
            .thenCheck { checkTextNameHasExactlyString(app.developer.name) }
            .thenCheck { checkTextPrivacyHasExactlyString(app.developer.privacy) }
    }
}
