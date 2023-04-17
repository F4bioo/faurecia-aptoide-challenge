package com.fappslab.features.home.data.repository

import com.fappslab.features.home.data.mapper.toApps
import com.fappslab.features.home.data.model.AppsResponse
import com.fappslab.features.home.data.source.HomeDataSourceImpl
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel
import com.fappslab.libraries.arch.network.exception.HttpThrowable
import com.fappslab.libraries.arch.testing.rules.RemoteTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class HomeRepositoryImplIntegrationTest {

    @get:Rule
    val remoteRule = RemoteTestRule()

    private lateinit var subject: HomeRepositoryImpl

    @Before
    fun setUp() {
        subject = HomeRepositoryImpl(
            dataSource = HomeDataSourceImpl(
                service = remoteRule.createTestService()
            )
        )
    }

    @Test
    fun `getAppsSuccess Should emit Apps When invoke`() {
        // Given
        val expectedResult = readFromJSONToModel<AppsResponse>(GET_APPS_SUCCESS_RESPONSE)
        remoteRule.toServerSuccessResponse()

        // When
        val result = subject.getApps()

        // Then
        result.test().assertValue { apps ->
            expectedResult.toApps() == apps
        }
    }

    @Test
    fun `getAppsFailure Should emit Exception When invoke`() {
        // Given
        val expectedResult = "Unexpected error, please try again."
        remoteRule.toServerFailureResponse()

        // When
        val result = subject.getApps()

        // Then
        result.test().assertError { cause ->
            cause is HttpThrowable && cause.message == expectedResult
        }
    }
}
