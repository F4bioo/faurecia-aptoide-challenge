package com.fappslab.features.home.domain.usecase

import com.fappslab.features.home.domain.repository.HomeRepository
import com.fappslab.features.home.domain.stub.appsStub
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Test
import kotlin.test.assertEquals

internal class GetAppsUseCaseTest {

    private val repository = mockk<HomeRepository>()
    private val subject = GetAppsUseCase(repository)

    @Test
    fun `getAppsSuccess Should emit Apps When invoke`() {
        // Given
        val expectedResult = Single.just(appsStub())
        every { repository.getApps() } returns expectedResult

        // When
        val result = subject()

        // Then
        assertEquals(expectedResult, result)
        verify { repository.getApps() }
    }

    @Test
    fun `getAppsFailure Should emit Exception When invoke`() {
        // Given
        val cause = Throwable("Some error")
        every { repository.getApps() } returns Single.error(cause)

        // Then
        subject()
            .test()
            .assertError { it.message == "Some error" }
        verify { repository.getApps() }
    }
}
