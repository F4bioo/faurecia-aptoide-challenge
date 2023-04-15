package com.fappslab.features.home.data.repository

import com.fappslab.features.home.data.mapper.toApps
import com.fappslab.features.home.data.model.AppsResponse
import com.fappslab.features.home.data.source.HomeDataSourceImpl
import com.fappslab.libraries.arch.jsonhandle.readFromJSONToModel
import com.fappslab.libraries.arch.rules.RemoteTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.rx2.rxSingle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
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
        val expectedResult = readFromJSONToModel<AppsResponse>(GET_LIST_APP_SUCCESS_RESPONSE)
        remoteRule.toServerSuccessResponse()

        // When
        val result = subject.getApps()

        // Then
        runTest {
            assertEquals(expectedResult.toApps(), result.await())
        }
    }

    @Test
    fun `getAppsFailure Should emit Exception When invoke`() {
        // Given
        remoteRule.toServerFailureResponse()

        // When
        val result = rxSingle { subject.getApps().blockingGet() }

        // Then
        runTest {
            assertFailsWith<HttpException> { result.await() }
        }
    }
}
