package com.fappslab.features.details.domain.usecase

import com.fappslab.features.details.domain.repository.DetailsRepository
import com.fappslab.features.details.stub.appStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test
import kotlin.test.assertEquals

internal class GetAppUseCaseTest {

    private val repository = mockk<DetailsRepository>()
    private val subject = GetAppUseCase(repository)

    @Test
    fun `getAppSuccess Should emit Apps When invoke`() {
        // Given
        val app = appStub()
        val expectedResult = Single.just(app)
        every { repository.getApp(any()) } returns expectedResult

        // When
        val result = subject(packageName = "com.instagram.android")

        // Then
        assertEquals(expectedResult, result)
        verify { repository.getApp(any()) }
    }

    @Test
    fun `getAppFailure Should emit Exception When invoke`() {
        // Given
        val cause = Throwable("Some error")
        every { repository.getApp(any()) } returns Single.error(cause)

        // Then
        subject(packageName = "com.instagram.android")
            .test()
            .assertError { it.message == "Some error" }
        verify { repository.getApp(any()) }
    }
}
