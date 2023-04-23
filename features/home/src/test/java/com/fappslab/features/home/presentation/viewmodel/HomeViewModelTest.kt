package com.fappslab.features.home.presentation.viewmodel

import app.cash.turbine.test
import com.fappslab.aptoide.features.home.R
import com.fappslab.features.home.domain.usecase.GetAppsUseCase
import com.fappslab.features.home.stub.ReturnsType
import com.fappslab.features.home.stub.appStub
import com.fappslab.features.home.stub.appsStub
import com.fappslab.libraries.arch.testing.rules.DispatcherTestRule
import com.fappslab.libraries.arch.testing.rules.SchedulerTestRule
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
internal class HomeViewModelTest {

    @get:Rule
    val schedulerRule = SchedulerTestRule()

    @get:Rule
    val dispatcherRule = DispatcherTestRule()

    private val initialState = HomeViewState()
    private val getAppsUseCase: GetAppsUseCase = mockk()
    private lateinit var subject: HomeViewModel

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `initSuccess Should expose expected state When invoke init block`() {
        //
        val apps = appsStub()
        val expectedState = initialState.copy(apps = apps.list)

        // When
        setupSubject(with = ReturnsType.SUCCESS)

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    @Test
    fun `initFailure Should expose expected state When invoke init block`() {
        // Given
        val expectedState = initialState.copy(flipperChild = EMPTY_CHILD, shouldAnimLottie = true)

        // When
        setupSubject()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    @Test
    fun `onClickItem Should expose expected Details action when invoke click item`() {
        // Given
        val app = appStub()
        val expectedAction = HomeViewAction.Details(app.packageName)
        setupSubject()

        // When
        subject.onClickItem(app)

        // Then
        runTest {
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    @Test
    fun `onSubmitList Should expose expected state When invoke submit list`() {
        // Given
        val apps = appsStub()
        val expectedState = initialState.copy(apps = apps.list, flipperChild = SUCCESS_CHILD)
        setupSubject(with = ReturnsType.SUCCESS)

        // When
        subject.onSubmitList()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    @Test
    fun `onRefreshSuccess Should expose expected state When invoke refresh`() {
        // Given
        val apps = appsStub()
        val expectedState = initialState.copy(apps = apps.list)
        setupSubject(with = ReturnsType.SUCCESS)

        // When
        subject.onRefresh()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase() }
    }

    @Test
    fun `onRefreshFailure Should expose expected state and Error action When invoke refresh`() {
        // Given
        val apps = appsStub()
        val message = "Some error"
        val expectedState = initialState.copy(apps = apps.list)
        val expectedAction = HomeViewAction.Error(message = message)
        setupSubject(with = ReturnsType.SUCCESS)
        every { getAppsUseCase() } returns Single.error(Throwable(message))

        // When
        subject.onRefresh()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase() }
    }

    @Test
    fun `onRefreshFailure Should expose expected state and Error action When invoke refresh with populated list`() {
        // Given
        val apps = appsStub()
        val message = "Some error"
        val expectedState = initialState.copy(apps = apps.list)
        val expectedAction = HomeViewAction.Error(message = message)
        setupSubject(with = ReturnsType.SUCCESS)
        every { getAppsUseCase() } returns Single.error(Throwable(message))

        // When
        subject.onRefresh()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase() }
    }

    @Test
    fun `onEmptyViewClickedSuccess Should expose expected state When invoke empty view button clicked`() {
        //
        val apps = appsStub()
        val expectedState = initialState.copy(apps = apps.list)
        setupSubject(with = ReturnsType.SUCCESS)

        // When
        subject.onEmptyButtonClicked()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase() }
    }

    @Test
    fun `onEmptyViewClickedFailure Should expose expected state and Error action When invoke empty view button clicked`() {
        // Given
        val apps = appsStub()
        val message = "Some error"
        val expectedState = initialState.copy(apps = apps.list)
        val expectedAction = HomeViewAction.Error(message = message)
        setupSubject(with = ReturnsType.SUCCESS)
        every { getAppsUseCase() } returns Single.error(Throwable(message))

        // When
        subject.onEmptyButtonClicked()

        // Then
        runTest {
            subject.state.test {
                assertEquals(expectedState, awaitItem())
            }
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify(exactly = 2) { getAppsUseCase() }
    }

    @Test
    fun `onMenuClicked Should expose About action When invoke menu clicked`() {
        // Given
        val expectedAction = HomeViewAction.About
        setupSubject()

        // When
        subject.onMenuClicked(idRes = R.id.about)

        // Then
        runTest {
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    private fun setupSubject(with: ReturnsType = ReturnsType.FAILURE) {
        every { getAppsUseCase() } returns with.type
        subject = HomeViewModel(
            getAppsUseCase = getAppsUseCase,
            scheduler = schedulerRule.testScheduler
        )
    }
}
