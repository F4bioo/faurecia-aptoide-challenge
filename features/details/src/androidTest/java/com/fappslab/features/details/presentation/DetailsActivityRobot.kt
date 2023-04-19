package com.fappslab.features.details.presentation

import android.content.Intent
import com.fappslab.features.details.presentation.viewmodel.DetailsViewAction
import com.fappslab.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.features.details.presentation.viewmodel.DetailsViewState
import com.fappslab.libraries.arch.testing.robot.robotview.RobotActivity
import com.fappslab.libraries.arch.testing.rules.ActivityTestRule
import com.fappslab.libraries.arch.testing.rules.DispatcherTestRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Rule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private typealias DetailsRobotActivitySingleAlias =
        RobotActivity<DetailsActivity, DetailsActivityRobotCheck,
                DetailsViewState, DetailsViewAction, DetailsViewModel>

@ExperimentalCoroutinesApi
internal class DetailsActivityRobot(
    intent: Intent
) : ActivityTestRule<DetailsActivity>(
    activityKClass = DetailsActivity::class,
    activityIntent = intent
), DetailsRobotActivitySingleAlias {

    @get:Rule
    val dispatcherRule = DispatcherTestRule(testDispatcher = UnconfinedTestDispatcher())

    private val fakeState = MutableStateFlow(DetailsViewState())
    private val fakeAction = MutableSharedFlow<DetailsViewAction>()
    private val fakeViewModel = mockk<DetailsViewModel>(relaxed = true) {
        every { state } returns fakeState
        every { action } returns fakeAction
    }

    override fun setupModules() = module {
        viewModel { fakeViewModel }
    }

    override fun givenState(
        state: () -> DetailsViewState
    ): DetailsRobotActivitySingleAlias {
        fakeState.update { state() }
        return this
    }

    override fun givenAction(
        invoke: DetailsViewModel.() -> Unit,
        action: () -> DetailsViewAction
    ): DetailsRobotActivitySingleAlias {
        every {
            invoke(fakeViewModel)
        } coAnswers {
            fakeAction.emit(action())
        }
        return this
    }

    override fun whenLaunch(
        activity: (DetailsActivity) -> Unit
    ): DetailsActivityRobotCheck {
        launchActivity { activity(it) }
        return DetailsActivityRobotCheck()
    }
}
