package com.ciandt.androidreference.provider.api

import android.support.test.filters.MediumTest
import android.support.test.filters.SmallTest
import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.provider.api.impl.GistApiProviderImpl
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class GistApiProviderTest : ProviderBaseTest<GistApi>(GistApi::class) {

    @Mock
    private lateinit var mockApi: GistApi

    @Mock
    private lateinit var publicCall: Call<Array<Gist>>

    @Mock
    private lateinit var response: Response<Array<Gist>>

    @Test
    @SmallTest
    fun publicGists_shouldReturnGists() {
        val gists = emptyArray<Gist>()

        `when`(response.body()).thenReturn(gists)
        `when`(response.isSuccessful).thenReturn(true)
        `when`(publicCall.execute()).thenReturn(response)
        `when`(mockApi.public()).thenReturn(publicCall)

        val provider = GistApiProviderImpl(mockApi)

        val result = provider.publicGists()

        verify(mockApi).public()
        assertSame(gists, result)
    }

    @Test
    @MediumTest
    fun publicGists_showReturnGists_integrated() {
        val provider = GistApiProviderImpl(api)

        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(arrayOf(Gist("Test")))))

        val result = provider.publicGists()

        assertFalse(result.isEmpty())
        assertEquals("Test", result.first().htmlUrl)
    }
}