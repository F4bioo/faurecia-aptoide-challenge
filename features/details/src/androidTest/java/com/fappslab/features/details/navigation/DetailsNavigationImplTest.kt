package com.fappslab.features.details.navigation

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fappslab.features.details.presentation.DetailsActivity
import com.fappslab.features.details.presentation.viewmodel.DetailsViewModel
import com.fappslab.features.details.presentation.viewmodel.DetailsViewState
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

@RunWith(AndroidJUnit4::class)
internal class DetailsNavigationImplTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create { modules(setupModules()) }

    private fun setupModules() = module {
        viewModel {
            mockk<DetailsViewModel>(relaxed = true) {
                every { state } returns MutableStateFlow(DetailsViewState())
                every { action } returns MutableSharedFlow()
            }
        }
    }

    @Test
    fun whenInvokeCreate_shouldOpenDetailsActivity() {
        // Given
        val context = ApplicationProvider.getApplicationContext<Context>()
        val subject = DetailsNavigationImpl()
        val expectedActivityName = "DetailsActivity"

        // When
        val intent = subject.create(context, packageName = "com.instagram.android")
        val scenario = ActivityScenario.launch<DetailsActivity>(intent)

        // Then
        scenario.onActivity { activity ->
            assertIs<DetailsActivity>(activity)
            assertEquals(expectedActivityName, activity.javaClass.simpleName)
        }
    }
}
