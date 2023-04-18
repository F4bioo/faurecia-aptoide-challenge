package com.fappslab.features.details.presentation.viewmodel

import app.cash.turbine.test
import com.fappslab.aptoide.libraries.design.R
import com.fappslab.features.details.domain.usecase.GetAppUseCase
import com.fappslab.features.details.stub.ReturnsType
import com.fappslab.features.details.stub.appStub
import com.fappslab.libraries.arch.testing.rules.DispatcherTestRule
import com.fappslab.libraries.arch.testing.rules.SchedulerTestRule
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
internal class DetailsViewModelTest {

    @get:Rule
    val schedulerRule = SchedulerTestRule()

    @get:Rule
    val dispatcherRule = DispatcherTestRule()

    private val initialState = DetailsViewState()
    private val getAppsUseCase: GetAppUseCase = mockk()
    private lateinit var subject: DetailsViewModel

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `initSuccess Should expose expected state When invoke init block`() {
        val app = appStub()
        val expectedState = initialState.copy(
            rankColor = R.color.ds_green,
            screenshots = app.content.screenshots,
            flipperChild = SUCCESS_CHILD,
            app = app
        )

        // When
        setupSubject(with = ReturnsType.SUCCESS)

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify { getAppsUseCase(any()) }
    }

    @Test
    fun `initFailure Should expose expected state When invoke init block`() {
        val expectedState = initialState.copy(
            errorMessage = "Some error",
            flipperChild = EMPTY_CHILD
        )

        // When
        setupSubject(with = ReturnsType.FAILURE)

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify { getAppsUseCase(any()) }
    }

    @Test
    fun `onBack Should expose expected FinishView action When invoke on back`() {
        // Given
        val expectedAction = DetailsViewAction.FinishView
        setupSubject()

        // When
        subject.onBack()

        // Then
        runTest {
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify { getAppsUseCase(any()) }
    }

    @Test
    fun `onTryAgainSuccess Should expose expected state When invoke try again`() {
        val app = appStub()
        val expectedState = initialState.copy(
            rankColor = R.color.ds_green,
            screenshots = app.content.screenshots,
            flipperChild = SUCCESS_CHILD,
            app = app
        )
        setupSubject(with = ReturnsType.SUCCESS)

        // When
        subject.onTryAgain()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase(any()) }
    }

    @Test
    fun `onTryAgainFailure Should expose expected state When invoke try again`() {
        val expectedState = initialState.copy(
            errorMessage = "Some error",
            flipperChild = EMPTY_CHILD
        )
        setupSubject(with = ReturnsType.FAILURE)

        // When
        subject.onTryAgain()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase(any()) }
    }

    @Test
    fun `onDownload Should expose expected Download action When invoke download`() {
        // Given
        val app = appStub()
        val expectedAction = DetailsViewAction.Download(apkUrl = app.apk.path, apkName = app.name)
        setupSubject(with = ReturnsType.SUCCESS)

        // When
        subject.onDownload()

        // Then
        runTest {
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify { getAppsUseCase(any()) }
    }

    private fun setupSubject(with: ReturnsType = ReturnsType.SUCCESS) {
        every { getAppsUseCase(any()) } returns with.type
        subject = DetailsViewModel(
            packageName = "com.instagram.android",
            getAppUseCase = getAppsUseCase,
            scheduler = schedulerRule.testScheduler
        )
    }
}
