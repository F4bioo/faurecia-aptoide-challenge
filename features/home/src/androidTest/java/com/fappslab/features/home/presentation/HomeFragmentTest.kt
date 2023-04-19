package com.fappslab.features.home.presentation

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fappslab.features.home.presentation.viewmodel.EMPTY_CHILD
import com.fappslab.features.home.presentation.viewmodel.HomeViewState
import com.fappslab.features.home.presentation.viewmodel.LOADING_CHILD
import com.fappslab.features.home.presentation.viewmodel.SUCCESS_CHILD
import com.fappslab.features.home.stub.appsStub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
internal class HomeFragmentTest {

    @get:Rule
    val robot = HomeFragmentRobot()

    private val apps = appsStub()
    private val initialState = HomeViewState()

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
    fun success_screen_Should_all_checks_pass_When_display_success_screen() {
        val successState = initialState.copy(
            flipperChild = SUCCESS_CHILD,
            apps = apps.list
        )

        robot
            .givenState { successState }
            .whenLaunch()
            .thenCheck { checkSuccessScreenIsDisplayed() }
            .thenCheck { checkToolbarHasExactlyBackgroundColor() }
            .thenCheck { checkEditorsTitleHasExactlyText() }
            .thenCheck { checkRecyclerViewEditorsIsPopulated() }
            .thenCheck { checkTopsTitleHasExactlyText() }
            .thenCheck { checkRecyclerViewTopsIsPopulated() }
            .thenCheck { checkTrendingHasExactlyText() }
            .thenCheck { checkRecyclerViewTrendingIsPopulated() }
    }

    @Test
    fun empty_screen_Should_all_checks_pass_When_display_empty_screen() {
        val emptyState = initialState.copy(
            shouldAnimLottie = false,
            flipperChild = EMPTY_CHILD,
            emptyViewMessage = EMPTY_SCREEN_MESSAGE
        )

        robot
            .givenState { emptyState }
            .whenLaunch()
            .thenCheck { checkEmptyScreenIsDisplayed() }
            .thenCheck { checkEmptyScreenHasExactlyText() }
            .thenCheck { checkEmptyScreenButtonIsClicked() }
    }
}
