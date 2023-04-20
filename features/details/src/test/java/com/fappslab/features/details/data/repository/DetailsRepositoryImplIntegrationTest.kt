package com.fappslab.features.details.data.repository

import com.fappslab.features.details.data.source.DetailsDataSourceImpl
import com.fappslab.features.details.stub.appStub
import com.fappslab.libraries.arch.network.exception.HttpThrowable
import com.fappslab.libraries.arch.testing.rules.RemoteTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class DetailsRepositoryImplIntegrationTest {

    @get:Rule
    val remoteRule = RemoteTestRule()

    private lateinit var subject: DetailsRepositoryImpl

    @Before
    fun setUp() {
        subject = DetailsRepositoryImpl(
            dataSource = DetailsDataSourceImpl(
                service = remoteRule.createTestService()
            )
        )
    }

    @Test
    fun `getAppSuccess Should emit Apps When invoke`() {
        // Given
        val expectedResult = appStub()
        remoteRule.toServerSuccessResponse()

        // When
        val result = subject.getApp(packageName = "com.instagram.android")

        // Then
        result.test().assertValue { app ->
            expectedResult == app
        }
    }

    @Test
    fun `getAppFailure Should emit Exception When invoke`() {
        // Given
        val expectedResult = "Application 'package:com.instagram.android' not found"
        remoteRule.toServerFailureResponse()

        // When
        val result = subject.getApp(packageName = "com.instagram.android")

        // Then
        result.test().assertError { cause ->
            cause is HttpThrowable && cause.message == expectedResult
        }
    }
}
