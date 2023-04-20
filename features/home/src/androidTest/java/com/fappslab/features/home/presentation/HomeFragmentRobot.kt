package com.fappslab.features.home.presentation

import com.fappslab.aptoide.libraries.design.R
import com.fappslab.features.home.presentation.viewmodel.HomeViewAction
import com.fappslab.features.home.presentation.viewmodel.HomeViewModel
import com.fappslab.features.home.presentation.viewmodel.HomeViewState
import com.fappslab.libraries.arch.testing.robot.robotview.RobotFragmentState
import com.fappslab.libraries.arch.testing.rules.DispatcherTestRule
import com.fappslab.libraries.arch.testing.rules.FragmentTestRule
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

private typealias HomeRobotFragmentSingleAlias =
        RobotFragmentState<HomeFragment, HomeFragmentRobotCheck,
                HomeViewState, HomeViewModel>

@ExperimentalCoroutinesApi
internal class HomeFragmentRobot : FragmentTestRule<HomeFragment>(
    fragmentKClass = HomeFragment::class,
    themeResId = R.style.Theme_Ds
), HomeRobotFragmentSingleAlias {

    @get:Rule
    val dispatcherRule = DispatcherTestRule(testDispatcher = UnconfinedTestDispatcher())

    private val fakeState = MutableStateFlow(HomeViewState())
    private val fakeAction = MutableSharedFlow<HomeViewAction>()
    private val fakeViewModel = mockk<HomeViewModel>(relaxed = true) {
        every { state } returns fakeState
        every { action } returns fakeAction
    }

    override fun setupModules() = module {
        viewModel { fakeViewModel }
    }

    override fun givenState(
        state: () -> HomeViewState
    ): HomeRobotFragmentSingleAlias {
        fakeState.update { state() }
        return this
    }

    override fun whenLaunch(
        fragment: (HomeFragment) -> Unit
    ): HomeFragmentRobotCheck {
        launchFragment { fragment(it) }
        return HomeFragmentRobotCheck()
    }
}
