package com.fappslab.features.home.navigation

import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fappslab.features.home.presentation.HomeFragment
import com.fappslab.features.home.presentation.viewmodel.HomeViewModel
import com.fappslab.features.home.presentation.viewmodel.HomeViewState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTestRule
import kotlin.test.assertEquals
import kotlin.test.assertIs
import com.fappslab.aptoide.libraries.design.R

@RunWith(AndroidJUnit4::class)
internal class HomeNavigationImplTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create { modules(setupModules()) }

    private fun setupModules() = module {
        viewModel {
            mockk<HomeViewModel>(relaxed = true) {
                every { state } returns MutableStateFlow(HomeViewState())
                every { action } returns MutableSharedFlow()
            }
        }
    }

    @Test
    fun whenInvokeCreate_shouldOpenHomeFragment() {
        // Given
        val subject = HomeNavigationImpl()
        val expectedFragmentName = "HomeFragment"

        // When
        val scenario = launchFragment(themeResId = R.style.Theme_Ds) {
            subject.create()
        }

        // Then
        scenario.onFragment { fragment ->
            assertIs<HomeFragment>(fragment)
            assertEquals(expectedFragmentName, fragment.javaClass.simpleName)
        }
    }
}
