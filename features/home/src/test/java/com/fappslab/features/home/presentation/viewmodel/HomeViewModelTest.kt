package com.fappslab.features.home.presentation.viewmodel

import app.cash.turbine.test
import com.fappslab.aptoide.features.home.R
import com.fappslab.features.home.domain.model.Apps
import com.fappslab.features.home.domain.usecase.GetAppsUseCase
import com.fappslab.features.home.stub.appStub
import com.fappslab.features.home.stub.appsStub
import com.fappslab.libraries.arch.testing.rules.ViewModelTestRule
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
    val viewModelRule = ViewModelTestRule()

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
        setupSubject { Single.just(apps) }

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
        val expectedState = initialState.copy(flipperChild = EMPTY_CHILD)

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
        setupSubject { Single.just(apps) }

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
        //
        val apps = appsStub()
        val expectedState = initialState.copy(apps = apps.list)
        setupSubject { Single.just(apps) }

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
        val expectedAction = HomeViewAction.Error(shouldShow = true, message = message)
        setupSubject { Single.just(apps) }
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
        val expectedAction = HomeViewAction.Error(shouldShow = true, message = message)
        setupSubject { Single.just(apps) }
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
    fun `onErrorDismiss Should expose Error action When invoke error dismiss with populated list`() {
        // Given
        val apps = appsStub()
        val expectedAction = HomeViewAction.Error(shouldShow = false, message = null)
        setupSubject { Single.just(apps) }

        // When
        subject.onErrorDismiss()

        // Then
        runTest {
            subject.action.test {
                assertEquals(expectedAction, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    @Test
    fun `onAboutDismiss Should expose About action When invoke about dismiss`() {
        // Given
        val expectedFirstAction = HomeViewAction.About(shouldShow = true)
        val expectedFinalAction = HomeViewAction.About(shouldShow = false)
        setupSubject()
        subject.onMenuClicked(idRes = R.id.about)

        // When
        subject.onAboutDismiss()

        // Then
        runTest {
            subject.action.test {
                assertEquals(expectedFirstAction, awaitItem())
                assertEquals(expectedFinalAction, awaitItem())
            }
        }
        verify { getAppsUseCase() }
    }

    @Test
    fun `onEmptyViewClickedSuccess Should expose expected state When invoke empty view button clicked`() {
        //
        val apps = appsStub()
        val expectedState = initialState.copy(apps = apps.list)
        setupSubject { Single.just(apps) }

        // When
        subject.onEmptyViewClicked()

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
        val expectedAction = HomeViewAction.Error(shouldShow = true, message = message)
        setupSubject { Single.just(apps) }
        every { getAppsUseCase() } returns Single.error(Throwable(message))

        // When
        subject.onEmptyViewClicked()

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
        val expectedAction = HomeViewAction.About(shouldShow = true)
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

    private fun setupSubject(everyBlock: () -> Single<Apps> = { Single.error(Throwable()) }) {
        every { getAppsUseCase() } returns everyBlock()
        subject = HomeViewModel(
            getAppsUseCase = getAppsUseCase
        )
    }
}
