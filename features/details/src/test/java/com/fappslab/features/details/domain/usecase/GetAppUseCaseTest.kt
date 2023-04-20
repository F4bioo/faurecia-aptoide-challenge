package com.fappslab.features.details.domain.usecase

import com.fappslab.features.details.domain.repository.DetailsRepository
import com.fappslab.features.details.stub.appStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test

internal class GetAppUseCaseTest {

    private val repository = mockk<DetailsRepository>()
    private val subject = GetAppUseCase(repository)

    @Test
    fun `getAppSuccess Should emit Apps When invoke`() {
        // Given
        val expectedResult = appStub()
        every { repository.getApp(any()) } returns Single.just(expectedResult)

        // When
        val result = subject(packageName = "com.instagram.android")

        // Then
        result.test().assertValue { app ->
            expectedResult == app
        }
        verify { repository.getApp(any()) }
    }

    @Test
    fun `getAppFailure Should emit Exception When invoke`() {
        // Given
        val expectedResult = "Some error"
        every { repository.getApp(any()) } returns Single.error(Throwable(expectedResult))

        // When
        val result = subject(packageName = "com.instagram.android")

        // Then
        result.test().assertError { cause ->
            expectedResult == cause.message
        }
        verify { repository.getApp(any()) }
    }
}
