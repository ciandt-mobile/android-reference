package com.ciandt.androidreference.provider.api

import android.support.test.filters.MediumTest
import android.support.test.filters.SmallTest
import com.ciandt.androidreference.entity.gist.Gist
import com.ciandt.androidreference.provider.api.impl.GistApiProviderImpl
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertFalse
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
    lateinit var mockApi: GistApi

    @Mock
    lateinit var publicCall: Call<Array<Gist>>

    @Mock
    lateinit var response: Response<Array<Gist>>

    @Test
    @SmallTest
    fun publicGists_shouldReturnGists() {
        `when`(response.body()).thenReturn(emptyArray())
        `when`(response.isSuccessful).thenReturn(true)
        `when`(publicCall.execute()).thenReturn(response)
        `when`(mockApi.public()).thenReturn(publicCall)

        val provider = GistApiProviderImpl(mockApi)

        provider.publicGists()

        verify(mockApi).public()
    }

    @Test
    @MediumTest
    fun publicGists_showReturnGists_integrated() {
        val provider = GistApiProviderImpl(api)

        mockWebServer.enqueue(MockResponse().setBody(Gson().toJson(arrayOf(Gist("")))))

        assertFalse(provider.publicGists().isEmpty())
    }
}