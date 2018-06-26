package com.ciandt.androidreference.business

import android.support.test.filters.MediumTest
import com.ciandt.androidreference.business.data.Failure
import com.ciandt.androidreference.business.data.Success
import com.ciandt.androidreference.business.impl.GistBusinessImpl
import com.ciandt.androidreference.entity.error.Error
import com.ciandt.androidreference.entity.error.Network
import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.provider.api.GistApi
import com.ciandt.androidreference.provider.api.GistApiProvider
import com.ciandt.androidreference.provider.api.impl.GistApiProviderImpl
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GistBusinessTest {

    @Mock
    private lateinit var gistApiProvider: GistApiProvider

    @Mock
    private lateinit var api: GistApi

    @Mock
    private lateinit var publicCall: Call<Array<Gist>>

    @Mock
    private lateinit var response: Response<Array<Gist>>

    private lateinit var gistBusiness: GistBusiness

    private val gists = emptyArray<Gist>()

    @Before
    fun setup() {
        gistBusiness = GistBusinessImpl(gistApiProvider)
    }

    @Test
    @MediumTest
    fun publicGists_shouldReturnGists() {
        `when`(gistApiProvider.publicGists()).thenReturn(gists)

        runBlocking {
            val result = gistBusiness.publicGists().await() as Success<Array<Gist>>

            verify(gistApiProvider).publicGists()
            assertSame(gists, result.data)
        }
    }

    @Test
    @MediumTest
    fun publicGist_shouldThrowException() {

        `when`(gistApiProvider.publicGists()).thenThrow(Error(Network))

        runBlocking {
            val result = gistBusiness.publicGists().await()

            assertTrue(result is Failure)
            assertEquals(Network, (result as Failure).error.type)
            verify(gistApiProvider).publicGists()
        }

    }

    @Test
    @MediumTest
    fun publicGists_shouldReturnGists_integrated() {

        val gists = emptyArray<Gist>()

        `when`(response.body()).thenReturn(gists)
        `when`(response.isSuccessful).thenReturn(true)
        `when`(publicCall.execute()).thenReturn(response)
        `when`(api.public()).thenReturn(publicCall)

        val provider = GistApiProviderImpl(api)
        val business = GistBusinessImpl(provider)

        runBlocking {
            val result = business.publicGists().await() as Success<Array<Gist>>

            verify(api).public()
            assertSame(gists, result.data)
        }
    }
}