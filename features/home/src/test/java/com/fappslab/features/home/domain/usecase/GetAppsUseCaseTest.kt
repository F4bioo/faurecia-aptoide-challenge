package com.fappslab.features.home.domain.usecase

import com.fappslab.features.home.domain.repository.HomeRepository
import com.fappslab.features.home.stub.appsStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

internal class GetAppsUseCaseTest {

    private val repository = mockk<HomeRepository>()
    private val subject = GetAppsUseCase(repository)

    @Test
    fun `getAppsSuccess Should emit Apps When invoke`() {
        // Given
        val expectedResult = appsStub()
        every { repository.getApps() } returns Single.just(expectedResult)

        // When
        val result = subject()

        // Then
        result.test().assertValue { app ->
            expectedResult == app
        }
        verify { repository.getApps() }
    }

    @Test
    fun `getAppsFailure Should emit Exception When invoke`() {
        // Given
        val expectedResult = "Some error"
        every { repository.getApps() } returns Single.error(Throwable(expectedResult))


        // When
        val result = subject()

        // Then
        result.test().assertError { cause ->
            expectedResult == cause.message
        }
        verify { repository.getApps() }
    }
}
